package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteKdSearchService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 快递鸟--快递查询服务 熔断
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */

@Component
public class KdSearchServiceFallback implements RemoteKdSearchService {

    @Override
    public Result searchKdInfo(String shipperCode, String logisticCode, String mobile) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
