package com.hszn.nbmh.prevent.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.feign.RemoteUserIntegralService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:13 22/9/1
 * @Modified By:
 */

@Component
public class UserIntegralServiceFallback implements RemoteUserIntegralService {

    @Override
    public Result submit(List<NbmhUserIntegralRecord> params) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
