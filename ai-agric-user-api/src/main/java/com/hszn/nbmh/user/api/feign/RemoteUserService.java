package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.fallback.UserServiceFallback;
import com.hszn.nbmh.user.api.params.input.AnimalDoctorRegisterParam;
import com.hszn.nbmh.user.api.params.input.RegisterParam;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.NBMH_USER, fallback = UserServiceFallback.class)
public interface RemoteUserService {

    @PostMapping("/queryUserByPhone")
    Result<LoginUser> queryUserByPhone(@RequestParam("phone") String phone, @RequestHeader(SecurityConstants.FROM) String from);

    @PostMapping("/getByPhone")
    Result<LoginUser> getByPhone(@RequestParam("phone") String phone);

    @PostMapping("/checkUserExist")
    Result checkUserExist(@RequestParam("userName") String userName, @RequestHeader(SecurityConstants.FROM) String from);

    @PostMapping("/queryCurUserInfo")
    Result<CurUserInfo> queryCurUserInfo(@RequestParam("userId") Long userId, @RequestParam(value = "type", required = false) Integer type);

    @PostMapping("/registerUser")
    Result registerUser(@RequestBody RegisterParam param);


    @PostMapping("/integralUpdate")
    Result integralUpdate(@RequestBody NbmhUser param);

    @PostMapping("/animalDoctorRegister")
    Result animalDoctorRegister(@RequestBody AnimalDoctorRegisterParam param);

    @PostMapping("/animalDoctor/popular")
    Result<List<NbmhAnimalDoctorDetail>> popular(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail);

    @PostMapping("/animalDoctor/nearby")
    Result<List<NbmhAnimalDoctorDetail>> nearby(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail, @RequestParam("distance") double distance, @RequestParam("userLng") double userLng, @RequestParam("userLat") double userLat);

    @PostMapping("/{id}")
    Result<NbmhUser> getById(@PathVariable(value = "id") @NotNull Long id);
}