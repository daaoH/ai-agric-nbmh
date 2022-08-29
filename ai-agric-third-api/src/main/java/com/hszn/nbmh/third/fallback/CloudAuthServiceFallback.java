package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteCloudAuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 阿里云 增强版实人认证 熔断
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */

@Component
public class CloudAuthServiceFallback implements RemoteCloudAuthService {

    @Override
    public Result initSmartVerify(@RequestParam String metaInfo, @RequestParam String certName, @RequestParam String certNo, @RequestParam String mobile) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result describeSmartVerify(@RequestParam String certifyId) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
