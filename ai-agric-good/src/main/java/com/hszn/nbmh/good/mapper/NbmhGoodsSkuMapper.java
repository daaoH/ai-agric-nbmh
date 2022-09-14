package com.hszn.nbmh.good.mapper;

import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * sku库存 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
public interface NbmhGoodsSkuMapper extends BaseMapper<NbmhGoodsSku> {

    /**
     * 锁定库存
     * @param skuId
     * @param num
     * @return
     */
    Boolean lockSkuStock(@Param("skuId") Long skuId,  @Param("num") Integer num);

    /**
     * 解锁库存
     * @param skuId
     * @param num
     */
    Boolean unLockStock(@Param("skuId") Long skuId, @Param("num") Integer num);
}
