package com.hszn.nbmh.admin.api.feign;

import com.hszn.nbmh.admin.api.constant.AdminPathConstant;
import com.hszn.nbmh.admin.api.fallback.SysUserServiceFallback;
import com.hszn.nbmh.admin.api.params.vo.SysLoginUser;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午10:12 22/9/6
 * @Modified By:
 */
@FeignClient(contextId = "remoteSysUserService", value = ServiceNameConstant.SYS_USER_SERVICE, path = AdminPathConstant.SYS_USER, fallback = SysUserServiceFallback.class)
public interface RemoteSysUserService {

    @GetMapping("/info/{username}")
    Result<SysLoginUser> info(@PathVariable String username, @RequestHeader(SecurityConstants.FROM) String from);
}
