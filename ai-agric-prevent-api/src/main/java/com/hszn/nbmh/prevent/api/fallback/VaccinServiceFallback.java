package com.hszn.nbmh.prevent.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.feign.RemoteVaccinService;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import org.springframework.stereotype.Component;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:13 22/8/16
 * @Modified By:
 */

@Component
public class VaccinServiceFallback implements RemoteVaccinService {

    @Override
    public Result submit(VaccinParam param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result record(QueryRequest<VaccinParam> param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result details(NbmhVaccin vaccin) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getNum(NbmhVaccin param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
