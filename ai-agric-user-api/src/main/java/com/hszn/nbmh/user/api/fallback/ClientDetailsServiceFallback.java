package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.hszn.nbmh.user.api.feign.RemoteClientDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午1:05 22/8/17
 * @Modified By:
 */

@Component
public class ClientDetailsServiceFallback implements RemoteClientDetailsService {

    @Override
    public Result<SysOauthClientDetails> getClientDetailsById(String clientId, String from) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<SysOauthClientDetails>> listClientDetails(String from) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
