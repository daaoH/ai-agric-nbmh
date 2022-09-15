package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.mould.QueryRequest;

import com.hszn.nbmh.user.api.entity.NbmhVipPrice;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 * 用户vip价格对照表 服务类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
public interface INbmhVipPriceService extends IService<NbmhVipPrice> {

    IPage<NbmhVipPrice> getByPage(QueryRequest<NbmhVipPrice> nbmhVipPrice);

    List<NbmhVipPrice> getByParam(NbmhVipPrice nbmhVipPrice);
}
