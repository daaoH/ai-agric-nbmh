package com.hszn.nbmh.good.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hszn.nbmh.good.api.params.input.QueryGoodsParams;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品基本信息表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
public interface NbmhGoodsMapper extends BaseMapper<NbmhGoods> {

    IPage<NbmhGoods> queryGoodsByParams(IPage<NbmhGoods> page, @Param("params") QueryGoodsParams params);
}
