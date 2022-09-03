package com.hszn.nbmh.good.service;

import com.hszn.nbmh.common.redis.cache.CachePrefix;
import com.hszn.nbmh.good.api.params.vo.CartItemVo;
import com.hszn.nbmh.good.api.params.vo.CartVo;

import java.util.concurrent.ExecutionException;

/**
 * @Author：袁德民
 * @Description: 购物车接口
 * @Date:下午3:54 22/9/2
 * @Modified By:
 */
public interface ShoppingCartService {

    /**
     * 获取购物车缓存
     *
     * @param id
     * @return 用户ID
     */
    static String getCacheKeys(Long id) {
        return CachePrefix.SHOPPING_CART.getPrefix() + id;
    }

    /**
     * 加入购物车
     * @param skuId
     * @param num
     * @return
     */
    CartItemVo addCart(Long skuId, Integer num);

    /**
     * 获取购物车里面的信息
     * @return
     */
    CartVo getCart();

    /**
     * 获取购物车的购物项
     * @param skuId
     * @return
     */
    CartItemVo getCartItem(Long skuId);

    /**
     * 清空购物车的数据
     * @param cartKey
     */
    public void clearCartInfo(String cartKey);

    /**
     * 勾选购物项
     * @param skuId
     * @param check
     */
    void checkItem(Long skuId, Integer check);

    /**
     * 改变商品数量
     * @param skuId
     * @param num
     */
    void changeItemCount(Long skuId, Integer num);


    /**
     * 删除购物项
     * @param skuId
     */
    void deleteIdCartInfo(Long skuId);
}
