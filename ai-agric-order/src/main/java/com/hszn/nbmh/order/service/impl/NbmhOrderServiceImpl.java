package com.hszn.nbmh.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.constant.TimeConstant;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.redis.cache.CachePrefix;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.feign.RemoteGoodsSkuService;
import com.hszn.nbmh.good.api.params.vo.CartItemVo;
import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.order.api.constant.OrderStatusEnum;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.api.params.input.*;
import com.hszn.nbmh.order.api.params.out.SettlementReturn;
import com.hszn.nbmh.order.api.params.vo.FreightVo;
import com.hszn.nbmh.order.api.params.vo.SkuStockVo;
import com.hszn.nbmh.order.mapper.NbmhOrderMapper;
import com.hszn.nbmh.order.rabbitmq.OrderSender;
import com.hszn.nbmh.order.service.INbmhOrderItemService;
import com.hszn.nbmh.order.service.INbmhOrderService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhOrderServiceImpl extends ServiceImpl<NbmhOrderMapper, NbmhOrder> implements INbmhOrderService {

    @Resource
    private INbmhOrderItemService itemService;

    @Autowired
    private NbmhOrderMapper orderMapper;

    @Autowired
    private RemoteGoodsSkuService goodsSkuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderSender sender;


    @Autowired
    private RemoteUserService userService;

    private SnowFlakeIdUtil snowFlakeIdUtil = new SnowFlakeIdUtil(0, 0);

    @Override
    public Page<NbmhOrder> search(QueryRequest<OrderSearchParam> param) {
        String name = param.getQueryEntity().getName();
        LambdaQueryWrapper<NbmhOrder> wrapper;
        // TODO: 2022/9/1 userId需统一处理
        String userId = "userId";
        Page<NbmhOrder> result;
        Page<NbmhOrder> page = new Page<>(param.getPageNum(), param.getPageSize());
        if (StringUtils.hasText(name)) {
            //根据订单商品name模糊搜索订单id,获取订单列表
            List<NbmhOrderItem> items = itemService.findByName(name, userId);
            Set<Long> orderIds = items.stream().map(NbmhOrderItem::getOrderId).collect(Collectors.toSet());
            wrapper = Wrappers.lambdaQuery();
            wrapper.in(NbmhOrder::getId, orderIds);
        } else {
            Integer status = param.getQueryEntity().getOrderStatus();
            //根据订单状态获取订单列表
            wrapper = Wrappers.lambdaQuery();
            wrapper.eq(NbmhOrder::getStatus, status);
            wrapper.eq(NbmhOrder::getUserId, userId);
        }
        result = this.page(page, wrapper);
        result.getRecords().forEach(e -> e.setOrderItems(itemService.findByOrderId(e.getId())));
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createOrder(CreateOrderParam orderParam, NbmhUserAddress address, Long orderId) {
        Boolean createRet = false;
        try {
            List<PreOrderParam> preOrderItems = orderParam.getPreOrderItems();
            BigDecimal totalAmount = orderParam.getAmount();
            Boolean success = false;
            BigDecimal totalDiscountAmount = BigDecimal.ZERO;
            BigDecimal totalFreightAmount = BigDecimal.ZERO;
            List<NbmhOrderItem> allItems = new ArrayList<>();
            for(PreOrderParam preOrderParam : preOrderItems){
                ShopCartItemVo cartItemVo = preOrderParam.getCartItems();

                List<CartItemVo> cartItems = cartItemVo.getItems();
                BigDecimal itemTotalAmount = getTotalAmount(cartItems);
                List<NbmhOrderItem> orderItems = getOrderItemList(cartItems, orderId);

                //计算运费
                FreightVo freightVo = getFreightAmount(cartItemVo, address);
                BigDecimal freightAmount = freightVo.getPrice();
                if(ObjectUtil.isNull(freightAmount)){
                    freightAmount = BigDecimal.ZERO;
                }

                //优惠金额计算
                BigDecimal discountAmount = BigDecimal.ZERO;

                Set<SkuStockVo> skuSet = new HashSet<>();
                skuSet.addAll(cartItems.stream().map(item -> new SkuStockVo(item.getSkuId(), item.getCount()))
                        .collect(Collectors.toSet()));

                //清除购物车数据

                //扣减库存
                for(SkuStockVo stockVo : skuSet){
                    Result<Boolean> ret = goodsSkuService.lockstock(stockVo.getSkuId(), stockVo.getNum());
                    success = ret.getData();
                    if(!success){
                        throw new ServiceException(CommonEnum.STOCK_REDUCE_ERROR.getMsg());
                    }
                }
                allItems.addAll(orderItems);
                itemService.saveBatch(orderItems);
            }

            if(success){
                //创建订单
                NbmhOrder order = new NbmhOrder();
                order.setId(orderId);
                order.setOrderItems(allItems);
                order.setDiscountsAmount(totalDiscountAmount);
                order.setOrderStatus(OrderStatusEnum.WAIT_FOR_PAY.getCode());
                order.setFreightAmount(totalFreightAmount);

                //实际支付金额 = 应付金额 + 运费 -优惠金额
                BigDecimal payAmount = NumberUtil.add(totalAmount, totalFreightAmount);
                payAmount = NumberUtil.sub(payAmount, totalDiscountAmount);
                order.setPayAmount(payAmount);

                order.setStatus(0);

                orderMapper.insert(order);


                createRet = true;
            }else{
                OrderFailMessage failMessage = new OrderFailMessage();
                sender.sendCreateOrderFailMessage(failMessage);
                redisTemplate.opsForValue().setIfPresent("Order:Failed:" + orderId.toString(), CommonEnum.STOCK_REDUCE_ERROR.getMsg(), TimeConstant.ONE_DAY, TimeUnit.SECONDS);
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
            redisTemplate.opsForValue().setIfPresent("Order:Failed:" + orderId.toString(), e.getMessage(), TimeConstant.ONE_DAY, TimeUnit.SECONDS);
        }

        return createRet;
    }

    /**
     * 计算运费
     * @param cartItemVo
     * @param address
     * @return
     */
    private FreightVo getFreightAmount(ShopCartItemVo cartItemVo, NbmhUserAddress address) {
        FreightVo freightVo = new FreightVo();

        return freightVo;
    }

    /**
     * 获取订单项列表
     * @param cartItems
     * @param orderId
     * @return
     */
    private List<NbmhOrderItem> getOrderItemList(List<CartItemVo> cartItems, Long orderId) {
        List<NbmhOrderItem> itemList = new LinkedList<>();
        for(CartItemVo itemVo : cartItems){
            NbmhOrderItem orderItem = new NbmhOrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setShopId(itemVo.getShopId());
            orderItem.setGoodsSkuId(itemVo.getSkuId());
            orderItem.setGoodsName(itemVo.getTitle());
            orderItem.setGoodsPic(itemVo.getImage());
            orderItem.setGoodsPrice(itemVo.getPrice());
            orderItem.setGoodsQuantity(itemVo.getCount());
            orderItem.setStatus(0);
            orderItem.setRealAmount(orderItem.getGoodsPrice().multiply(BigDecimal.valueOf(itemVo.getCount())));
            itemList.add(orderItem);
        }
        itemList.sort(Comparator.comparing(NbmhOrderItem::getRealAmount).reversed());

        return itemList;
    }

    /**
     * 计算购物项总价
     * @param cartItems
     * @return
     */
    private BigDecimal getTotalAmount(List<CartItemVo> cartItems) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(CartItemVo itemVo : cartItems){
            BigDecimal itemTotal = itemVo.getTotalPrice();
            totalAmount = totalAmount.add(itemTotal);
        }
        return totalAmount;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public void autoCancelOrder(Long order) {

    }

    @Override
    public String preCheckOrder(CreateOrderParam order) {
        AuthUser user = SecurityUtils.getUser();

//        Result<CurUserInfo> ret = userService.queryCurUserInfo(user.getId(), );
//
//        //检查账号
//        checkUser(ret.getData());

        //检查商品库存
        checkStock(order);

        //检查限购
        checkLimitBuy(order);

        //检查优惠券
        checkCoupon(order);

        //检查活动信息
        checkActivity(order);

        //检查积分
        checkPoints(order);

        //检查收货地址及计算运费
        checkLogistics(order);

        //消息队列创建订单
        Long orderId = snowFlakeIdUtil.nextId();
        CreateOrderMessage orderMessage = new CreateOrderMessage();
        orderMessage.setOrderId(orderId);
        orderMessage.setParam(order);
        sender.sendCreateOrderMessage(orderMessage);

        return String.valueOf(orderId);
    }

    @Override
    public List<SettlementReturn> settlement(SettlementParam param) {
        List<SettlementReturn> rets = new ArrayList<>();
        List<ShopCartItemVo> cartItems = param.getCartItems();
        if(CollectionUtil.isNotEmpty(cartItems)){
            cartItems.forEach(item -> {
                SettlementReturn ret = new SettlementReturn();
                ShopCartItemVo shopCartItemVo = new ShopCartItemVo();
                shopCartItemVo.setShopInfo(item.getShopInfo());
                shopCartItemVo.setItems(item.getItems());
                ret.setCartItems(shopCartItemVo);
                rets.add(ret);
            });
        }
        return rets;
    }

    private void checkLogistics(CreateOrderParam order) {
    }

    private void checkPoints(CreateOrderParam order) {
    }

    private void checkActivity(CreateOrderParam order) {
    }

    private void checkCoupon(CreateOrderParam order) {
    }

    /**
     * 检查限购
     * @param order
     */
    private void checkLimitBuy(CreateOrderParam order) {

    }

    /**
     * 检查库存
     * @param order
     */
    private void checkStock(CreateOrderParam order) {
        List<PreOrderParam> orderParams =  order.getPreOrderItems();
        for(PreOrderParam param : orderParams){
            ShopCartItemVo cartItemVo = param.getCartItems();
            List<CartItemVo> items = cartItemVo.getItems();
            for(CartItemVo item : items){
                Integer stock = (Integer) redisTemplate.opsForValue().get(CachePrefix.SKU_STOCK.getPrefix() + item.getSkuId());
                if(stock < item.getCount()){
                    Result<NbmhGoodsSku> ret = goodsSkuService.getGoodsSkuById(item.getSkuId());
                    if(ret.getCode() == 200) {
                        NbmhGoodsSku goodsSku = ret.getData();
                        stock = goodsSku.getStock();
                    }
                    if(stock < 1){
                        throw new ServiceException(CommonEnum.STOCK_NUM_ERROR.getMsg());
                    }

                }

                stock = stock - item.getCount();
                redisTemplate.opsForValue().set(CachePrefix.SKU_STOCK.getPrefix() + item.getSkuId(), stock);
            }
        }

    }

    private void checkUser(CurUserInfo userInfo) {
    }
}
