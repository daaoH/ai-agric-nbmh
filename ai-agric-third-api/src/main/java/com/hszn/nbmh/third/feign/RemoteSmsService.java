package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.OssUploadServiceFallback;
import com.hszn.nbmh.third.fallback.SmsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 阿里云 短信服务 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */

@FeignClient(value = ServiceNameConstant.THIRD_SERVICE, path = UrlPathConstant.ALY_SMS, fallback = SmsServiceFallback.class)
public interface RemoteSmsService {

    @PostMapping("/sendSms")
    Result sendSms(@RequestParam String phoneNumber);

    @PostMapping("/validateCode")
    Result validateCode(@RequestParam String phoneNumber, @RequestParam String code);

}
