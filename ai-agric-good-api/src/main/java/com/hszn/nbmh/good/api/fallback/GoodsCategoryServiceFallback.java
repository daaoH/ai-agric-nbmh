package com.hszn.nbmh.good.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.feign.RemoteGoodsCategoryService;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @Author：袁德民
 * @Description:
 * @Date:上午11:05 22/8/25
 * @Modified By:
 */

@Component
public class GoodsCategoryServiceFallback implements RemoteGoodsCategoryService {

    @Override
    public Result getFirstCategory() {
        return Result.failed(CommonEnum.FALL_BACK_MSG);
    }

    @Override
    public Result getSecondCategory(@NotNull Integer pid) {
        return Result.failed(CommonEnum.FALL_BACK_MSG);
    }
}
