package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteSmsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 阿里云 短信服务 熔断
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */

@Component
public class SmsServiceFallback implements RemoteSmsService {

    @Override
    public Result sendSms(@RequestParam String phoneNumber) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result validateCode(@RequestParam String phoneNumber, @RequestParam String code) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

}
