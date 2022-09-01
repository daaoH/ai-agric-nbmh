package com.hszn.nbmh.good.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.good.api.params.input.QueryGoodsParams;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
public interface INbmhGoodsService extends IService<NbmhGoods> {

    IPage<NbmhGoods> queryGoodsByParams(IPage<NbmhGoods> page, QueryGoodsParams params);

}
