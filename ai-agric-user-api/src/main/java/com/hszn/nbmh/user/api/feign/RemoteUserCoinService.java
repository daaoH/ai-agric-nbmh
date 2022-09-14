package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhUserCoinRecord;
import com.hszn.nbmh.user.api.fallback.UserCoinServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(contextId="remoteUserService", value=ServiceNameConstant.USER_SERVICE, path=UserPathConstant.NBMH_USER_COIN_EXPERT, fallback=UserCoinServiceFallback.class)
public interface RemoteUserCoinService {

    @PostMapping("/getByPage")
    Result getByPage(@RequestBody QueryRequest<NbmhUserCoinRecord> param);

    @PostMapping("/submit")
    Result submit(@RequestBody NbmhUserCoinRecord param);


}