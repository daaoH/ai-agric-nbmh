package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUserFootprint;
import com.hszn.nbmh.user.api.feign.RemoteFootprintService;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:29 22/8/25
 * @Modified By:
 */
@Component
public class FootprintServiceFallback implements RemoteFootprintService {

    @Override
    public Result addFootprint(NbmhUserFootprint footprint) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }
}
