package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.feign.RemotePreventService;
import com.hszn.nbmh.user.api.params.input.NbmhPreventStationParam;
import org.springframework.stereotype.Component;

/**
 * @Author：wangjun
 * @Description:
 * @Date:下午1:01 22/8/29
 * @Modified By:
 */
@Component
public class PreventServiceFallback implements RemotePreventService {


    @Override
    public Result add(NbmhPreventStationParam entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
