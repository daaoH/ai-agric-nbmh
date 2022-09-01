package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.input.RegisterParam;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午1:01 22/8/17
 * @Modified By:
 */
@Component
public class UserServiceFallback implements RemoteUserService {

    @Override
    public Result<LoginUser> queryUserByPhone(String phone, String from) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result checkUserExist(String userName, String from) {
        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }

    @Override
    public Result<CurUserInfo> queryCurUserInfo(Long userId, Integer type) {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }

    @Override
    public Result registerUser(RegisterParam param) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result integralUpdate(NbmhUser param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

}
