package com.hszn.nbmh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.api.params.input.CreateOrderParam;
import com.hszn.nbmh.order.mapper.NbmhOrderMapper;
import com.hszn.nbmh.order.service.INbmhOrderItemService;
import com.hszn.nbmh.order.service.INbmhOrderService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
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

    @Override
    public String createOrder(CreateOrderParam order) {
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

        return String.valueOf(orderId);
    }

    private void checkLogistics(CreateOrderParam order) {
    }

    private void checkPoints(CreateOrderParam order) {
    }

    private void checkActivity(CreateOrderParam order) {
    }

    private void checkCoupon(CreateOrderParam order) {
    }

    private void checkLimitBuy(CreateOrderParam order) {
    }

    /**
     * 检查库存
     * @param order
     */
    private void checkStock(CreateOrderParam order) {

    }

    private void checkUser(CurUserInfo userInfo) {
    }
}
