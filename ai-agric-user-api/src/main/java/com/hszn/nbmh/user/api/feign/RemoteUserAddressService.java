package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import com.hszn.nbmh.user.api.fallback.UserAddressServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午9:37 22/8/25
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.ADDRESS_PATH,
        fallback = UserAddressServiceFallback.class)
public interface RemoteUserAddressService {

    @PostMapping("/queryUserAddressList")
    Result<List<NbmhUserAddress>> queryUserAddressList(@RequestParam("userId") Long userId);

    @PostMapping("/queryDefaultAddress")
    Result<NbmhUserAddress> queryDefaultAddress(@RequestParam("userId") Long userId);

    @PostMapping("/addUserAddress")
    Result addUserAddress(@RequestBody NbmhUserAddress userAddress);

    @PostMapping("/updateUserAddress")
    Result updateUserAddress(@RequestBody NbmhUserAddress userAddress);
}
