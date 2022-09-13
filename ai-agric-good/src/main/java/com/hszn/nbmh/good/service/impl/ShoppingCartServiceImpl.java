package com.hszn.nbmh.good.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.generator.config.querys.GaussQuery;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.enums.GoodAuthEnum;
import com.hszn.nbmh.good.api.enums.GoodStatusEnum;
import com.hszn.nbmh.good.api.params.vo.CartItemVo;
import com.hszn.nbmh.good.api.params.vo.CartVo;
import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import com.hszn.nbmh.good.service.ShoppingCartService;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.shop.api.feign.RemoteShopInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author：袁德民
 * @Description: 购物车实现类
 * @Date:下午4:38 22/9/1
 * @Modified By:
 */

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private INbmhGoodsSkuService goodsSkuService;

    @Autowired
    private RemoteShopInfoService shopInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public CartItemVo addCart(Long skuId, Integer num) {
        if (num < 1) {
            throw new ServiceException(CommonEnum.CART_NUM_ERROR.getMsg());
        }
        /**
         * 检查商品是否正常
         */
//        NbmhGoodsSku goodsSku = checkGoods(skuId);
        NbmhGoodsSku goodsSku = goodsSkuService.getById(skuId);
        if (ObjectUtils.isEmpty(goodsSku)) {
            throw new ServiceException((CommonEnum.DATA_NOT_EXIST.getMsg()));
        }

        Result<NbmhShopInfo> ret = shopInfoService.detail(goodsSku.getShopId());
        NbmhShopInfo shopInfo = new NbmhShopInfo();
        if (ret.getCode() == 200) {
            shopInfo = ret.getData();
        }

        BoundHashOperations<String, Object, Object> carts = getCartOps();
        String goodsValue = (String) carts.get(skuId.toString());
        if (StringUtils.isEmpty(goodsValue)) {
            CartItemVo cartItem = new CartItemVo();

            cartItem.setShopId(shopInfo.getId());
            cartItem.setSkuId(goodsSku.getId());
            cartItem.setTitle(goodsSku.getSkuName());
            cartItem.setImage(goodsSku.getPic());
            cartItem.setPrice(goodsSku.getPrice());
            cartItem.setCount(num);

            String cartItemJson = JSON.toJSONString(cartItem);
            getCartOps().put(skuId.toString(), cartItemJson);
            return cartItem;
        } else {
            //购物车有该商品，修改数量
            CartItemVo cartItem = JSON.parseObject(goodsValue, CartItemVo.class);
            if (cartItem.getCount() + num > goodsSku.getStock()) {
                //选购数量大于库存量
                throw new ServiceException((CommonEnum.STOCK_NUM_ERROR.getMsg()));
            } else {
                cartItem.setCount(cartItem.getCount() + num);
            }

            String cartItemJson = JSON.toJSONString(cartItem);
            carts.put(skuId.toString(), cartItemJson);

            return cartItem;
        }

    }

    @Override
    public CartVo getCart() {
        CartVo cartVo = new CartVo();
        AuthUser authUser = SecurityUtils.getUser();
        if (ObjectUtils.isEmpty(authUser)) {
            //用户没有登录
            return null;
        } else {
            String cartKey = "";
            if (authUser.getId() != null) {
                cartKey = ShoppingCartService.getCacheKeys(authUser.getId());
            }

            List<CartItemVo> cartItems = getCartItems(cartKey);
            if (CollectionUtil.isNotEmpty(cartItems)) {
                Map<Long, List<CartItemVo>> maps = cartItems.stream().collect(Collectors.groupingBy(c -> c.getShopId()));
                List<ShopCartItemVo> cartItemVos = new ArrayList<>();
                for (Long key : maps.keySet()) {
                    ShopCartItemVo itemVo = new ShopCartItemVo();
                    Result<NbmhShopInfo> ret = shopInfoService.detail(key);
                    NbmhShopInfo shopInfo = ret.getData();
                    itemVo.setShopInfo(shopInfo);
                    itemVo.setItems(maps.get(key));
                    cartItemVos.add(itemVo);
                }

                cartVo.setShopCartItems(cartItemVos);
            }
            return cartVo;
        }
    }

    private List<CartItemVo> getCartItems(String cartKey) {
        //获取购物车里面的所有商品
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        List<Object> values = operations.values();
        if (values != null && values.size() > 0) {
            List<CartItemVo> cartItemVoStream = values.stream().map((obj) -> {
                String str = (String) obj;
                CartItemVo cartItem = JSON.parseObject(str, CartItemVo.class);
                return cartItem;
            }).collect(Collectors.toList());
            return cartItemVoStream;
        }
        return null;
    }

    @Override
    public CartItemVo getCartItem(Long skuId) {
        //拿到要操作的购物车信息
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        String redisValue = (String) cartOps.get(skuId.toString());

        CartItemVo cartItemVo = JSON.parseObject(redisValue, CartItemVo.class);

        return cartItemVo;
    }

    @Override
    public void clearCartInfo(String cartKey) {
        redisTemplate.delete(cartKey);
    }

    /**
     * 选中购物项
     *
     * @param skuId
     * @param check
     */
    @Override
    public void checkItem(Long skuId, Integer check) {
        //查询购物车里的商品
        CartItemVo cartItem = getCartItem(skuId);
        //修改商品状态
        cartItem.setCheck(check == 1 ? true : false);

        String strCartItem = JSON.toJSONString(cartItem);

        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(), strCartItem);
    }

    /**
     * 修改购物项数据
     *
     * @param skuId
     * @param num
     */
    @Override
    public void changeItemCount(Long skuId, Integer num) {
        //查询购物车里的商品
        CartItemVo cartItem = getCartItem(skuId);
        cartItem.setCount(num);

        BoundHashOperations<String, Object, Object> carts = getCartOps();
        //放入redis中
        String strCartItem = JSON.toJSONString(cartItem);
        carts.put(skuId.toString(), strCartItem);
    }

    /**
     * 删除购物项
     *
     * @param skuId
     */
    @Override
    public void deleteCartItem(List<Long> skuId) {
        BoundHashOperations<String, Object, Object> carts = getCartOps();
        if (CollectionUtil.isNotEmpty(skuId)) {
            for (Long id : skuId) {
                carts.delete(id.toString());
            }
        }
    }

    @Override
    public void checkItems(List<Long> skuIds, Integer check) {
        if(CollectionUtil.isNotEmpty(skuIds)){
            skuIds.forEach(id -> {
                checkItem(id, check);
            });
        }
    }

    //检测商品的有效性
    private NbmhGoodsSku checkGoods(Long skuId) {
        NbmhGoodsSku goodsSku = goodsSkuService.getGoodsSkuFromCache(skuId);
        if (ObjectUtils.isEmpty(goodsSku)) {
            throw new ServiceException(CommonEnum.DATA_NOT_EXIST.getMsg());
        }

        if (!GoodAuthEnum.PASS.code().equals(goodsSku.getStatus()) || !GoodStatusEnum.UP.code().equals(goodsSku.getGoodsStatus())) {
            throw new ServiceException(CommonEnum.DATA_NOT_EXIST.getMsg());
        }

        return goodsSku;
    }

    private BoundHashOperations<String, Object, Object> getCartOps() {
        //先得到当前用户信息
        AuthUser userInfoTo = SecurityUtils.getUser();
        if (ObjectUtils.isEmpty(userInfoTo)) {
            throw new ServiceException("请先登录");
        }
        String cartKey = "";
        if (userInfoTo.getId() != null) {
            cartKey = ShoppingCartService.getCacheKeys(userInfoTo.getId());
        }

        //绑定指定的key操作Redis
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);

        return operations;
    }
}
