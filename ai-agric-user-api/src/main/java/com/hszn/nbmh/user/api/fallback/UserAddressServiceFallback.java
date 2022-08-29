package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import com.hszn.nbmh.user.api.feign.RemoteUserAddressService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午9:39 22/8/25
 * @Modified By:
 */
@Component
public class UserAddressServiceFallback implements RemoteUserAddressService {

    @Override
    public Result<List<NbmhUserAddress>> queryUserAddressList(Long userId) {
        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }

    @Override
    public Result<NbmhUserAddress> queryDefaultAddress(Long userId) {
        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }

    @Override
    public Result addUserAddress(NbmhUserAddress userAddress) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result updateUserAddress(NbmhUserAddress userAddress) {
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }
}
