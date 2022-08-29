package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.SysLog;
import com.hszn.nbmh.user.api.feign.RemoteLogService;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午3:24 22/8/17
 * @Modified By:
 */

@Component
public class LogServiceFallback implements RemoteLogService {

    @Override
    public Result<Boolean> saveLog(SysLog sysLog, String from) {
        return null;
    }
}
