package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.SysLog;
import com.hszn.nbmh.user.api.fallback.LogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午3:23 22/8/17
 * @Modified By:
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstant.USER_SERVICE,
        path = UserPathConstant.SYS_LOG, fallback = LogServiceFallback.class)
public interface RemoteLogService {

    /**
     * 保存日志
     * @param sysLog 日志实体
     * @param from 内部调用标志
     * @return succes、false
     */
    @PostMapping("/savelog")
    Result<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);
}
