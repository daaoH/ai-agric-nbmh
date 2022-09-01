package com.hszn.nbmh.good.service;

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

    List<SkuVo> querySkuByGoodId(Long goodId);
}
