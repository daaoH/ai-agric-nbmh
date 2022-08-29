package com.hszn.nbmh.auth.api.feign;

import com.hszn.nbmh.auth.api.constant.AuthPathConstant;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午2:35 22/8/23
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.AUTH_SERVICE, path = "/test")
public interface AuthTestApi {

    @GetMapping("/getinfo")
    Result getInfo();
}
