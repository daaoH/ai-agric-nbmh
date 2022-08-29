package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.KdSearchEntity;

/**
 * <p>
 * 快递鸟--快递查询服务 接口类
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
public interface KdSearchService {

    KdSearchEntity searchKdInfo(String shipperCode, String logisticCode, String mobile);

}
