package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhUserExtract;
import com.hszn.nbmh.user.api.fallback.UserCoinServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value=ServiceNameConstant.USER_SERVICE, path=UserPathConstant.NBMH_USER_EXTRACT, fallback=UserCoinServiceFallback.class)
public interface RemoteUserExtractService {

    @PostMapping("/getByPage")
    Result getByPage(@RequestBody QueryRequest<NbmhUserExtract> param);

    @PostMapping("/submit")
    Result submit(@RequestBody NbmhUserExtract param);

}