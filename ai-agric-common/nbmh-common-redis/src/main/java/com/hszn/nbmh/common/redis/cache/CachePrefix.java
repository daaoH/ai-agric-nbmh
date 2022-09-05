package com.hszn.nbmh.common.redis.cache;

/**
 * @Author：袁德民
 * @Description: 缓存前缀
 * @Date:下午7:38 22/9/2
 * @Modified By:
 */
public enum CachePrefix {

    /**
     * 商品
     */
    GOODS,

    /**
     * 商品sku
     */
    GOODS_SKU,

    /**
     * sku库存
     */
    SKU_STOCK,

    /**
     * 购物车
     */
    SHOPPING_CART;

    /**
     * 获取缓存key值
     * @return
     */
    public String getPrefix(){
        return "{" + this.name() + "}_";
    }

}
