package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.CodeImageRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteCodeImageService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 二维码  熔断
 * </p>
 *
 * @author wangjun
 * @since 2022-08-18
 */

@Component
public class CodeImageServiceFallback implements RemoteCodeImageService {


    @Override
    public Result generate(CodeImageRequest param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
