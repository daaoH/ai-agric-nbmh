package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.CloudAuthServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 阿里云 增强版实人认证 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */

@FeignClient(value = ServiceNameConstant.THIRD_SERVICE, path = UrlPathConstant.CLOUD_AUTH, fallback = CloudAuthServiceFallback.class)
public interface RemoteCloudAuthService {

    @PostMapping("/initSmartVerify")
    Result initSmartVerify(@RequestParam String metaInfo, @RequestParam String certName, @RequestParam String certNo, @RequestParam String mobile);

    @PostMapping("/describeSmartVerify")
    Result describeSmartVerify(@RequestParam String certifyId);

}
