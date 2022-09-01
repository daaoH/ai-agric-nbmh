package com.hszn.nbmh.prevent.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.fallback.UserIntegralServiceFallback;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午9:59 22/9/1
 * @Modified By:
 */

@FeignClient(value=ServiceNameConstant.PREVENT_SERVICE, path=UrlPathConstant.USER_INTEGRAL_RECORD, fallback=UserIntegralServiceFallback.class)
public interface RemoteUserIntegralService {

    @PostMapping("/submit")
    @Operation(summary="提交积分记录")
    Result submit(@RequestBody List<NbmhUserIntegralRecord> params);

}