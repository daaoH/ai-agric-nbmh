package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteBaseConfigService;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 配置
 * </p>
 *
 * @author wangjun
 * @since 2022-9-1
 */

@Component
public class BaseConfigServiceFallback implements RemoteBaseConfigService {

    @Override
    public Result getBySubject(String subject) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
