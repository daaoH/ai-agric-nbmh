package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhRecharge;
import com.hszn.nbmh.user.api.fallback.UserCoinServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value=ServiceNameConstant.USER_SERVICE, path=UserPathConstant.NBMH_USER_RECHARGE, fallback=UserCoinServiceFallback.class)
public interface RemoteUserRechargeService {

    @PostMapping("/getByPage")
    Result getByPage(@RequestBody QueryRequest<NbmhRecharge> param);

    @PostMapping("/submit")
    Result submit(@RequestBody NbmhRecharge param);

}