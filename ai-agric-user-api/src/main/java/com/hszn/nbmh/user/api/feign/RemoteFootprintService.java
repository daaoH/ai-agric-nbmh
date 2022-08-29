package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhUserFootprint;
import com.hszn.nbmh.user.api.fallback.FootprintServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:28 22/8/25
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.FOOTPRINT_PATH,
        fallback = FootprintServiceFallback.class)
public interface RemoteFootprintService {

    @PostMapping("/addFootprint")
    Result addFootprint(@RequestBody NbmhUserFootprint footprint);
}
