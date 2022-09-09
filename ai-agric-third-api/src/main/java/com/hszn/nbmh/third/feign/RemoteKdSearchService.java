package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.KdSearchServiceFallback;
import com.hszn.nbmh.third.fallback.SmsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 快递鸟--快递查询服务 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */

@FeignClient(value = ServiceNameConstant.THIRD_SERVICE, path = UrlPathConstant.KD_SEARCH, fallback = KdSearchServiceFallback.class)
public interface RemoteKdSearchService {

    @PostMapping("/searchKdInfo")
    Result searchKdInfo(@RequestParam String shipperCode, @RequestParam String logisticCode, @RequestParam(value = "mobile", required = false) String mobile);

}
