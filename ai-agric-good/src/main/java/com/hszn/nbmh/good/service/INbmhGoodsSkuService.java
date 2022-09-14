package com.hszn.nbmh.good.service;

import com.hszn.nbmh.common.redis.cache.CachePrefix;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.good.api.params.vo.SkuVo;

import java.util.List;

/**
 * <p>
 * sku库存 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
public interface INbmhGoodsSkuService extends IService<NbmhGoodsSku> {

    /**
     * 获取商品SKU缓存ID
     *
     * @param id
     * @return 商品SKU缓存ID
     */
    static String getCacheKeys(Long id) {
        return CachePrefix.GOODS_SKU.getPrefix() + id;
    }

    /**
     * 获取商品SKU库存缓存ID
     *
     * @param id
     * @return 商品SKU缓存ID
     */
    static String getStockCacheKey(Long id) {
        return CachePrefix.SKU_STOCK.getPrefix() + id;
    }

    NbmhGoodsSku getGoodsSkuFromCache(Long id);

    List<SkuVo> querySkuByGoodId(Long goodId);

    /**
     * 锁定库存
     * @param skuId
     * @param num
     */
    Boolean lockStock(Long skuId, Integer num);

    /**
     * 解锁订单
     * @param skuId
     * @param num
     */
    Boolean unlockStock(Long skuId, Integer num);
}
