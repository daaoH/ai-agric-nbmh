package com.hszn.nbmh.admin.api.fallback;

import com.hszn.nbmh.admin.api.feign.RemoteSysUserService;
import com.hszn.nbmh.admin.api.params.vo.SysLoginUser;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午10:14 22/9/6
 * @Modified By:
 */
@Component
public class SysUserServiceFallback implements RemoteSysUserService {

    @Override
    public Result<SysLoginUser> info(String username, String from) {
        return null;
    }
}
