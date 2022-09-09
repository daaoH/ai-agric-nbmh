package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.KdSearchEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 快递鸟--快递查询服务 接口类
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Validated
public interface KdSearchService {

    KdSearchEntity searchKdInfo(@NotBlank String shipperCode, @NotBlank String logisticCode, String mobile);

}
