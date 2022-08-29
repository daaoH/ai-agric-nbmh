package com.hszn.nbmh.user.controller;


import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import com.hszn.nbmh.user.service.INbmhUserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Tag(name = "user-address", description = "用户收货地址接口")
@RestController
@RequestMapping("/user-address")
public class NbmhUserAddressController {

    @Autowired
    private INbmhUserAddressService addressService;

    @Operation(description = "查询用户所有收货地址")
    @PostMapping("/queryUserAddressList")
    public Result<List<NbmhUserAddress>> queryUserAddressList(@RequestParam("userId") Long userId){
        List<NbmhUserAddress> addresses = addressService.list(Wrappers.<NbmhUserAddress>query().lambda().eq(NbmhUserAddress::getUserId, userId).eq(NbmhUserAddress::getStatus, 0));
        if(CollectionUtils.isNotEmpty(addresses)){
            return Result.ok(addresses);
        }

        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }

    @Operation(description = "查询用户默认收货地址")
    @PostMapping("/queryDefaultAddress")
    public Result<NbmhUserAddress> queryDefaultAddress(@RequestParam("userId") Long userId){
        NbmhUserAddress address = addressService.getOne(Wrappers.<NbmhUserAddress>query().lambda().eq(NbmhUserAddress::getUserId, userId).eq(NbmhUserAddress::getIsDefault, true).eq(NbmhUserAddress::getStatus, 0));
        if(ObjectUtils.isNotEmpty(address)){
            return Result.ok(address);
        }

        return Result.failed("您还没有收货地址");
    }

    @Operation(description = "新增用户收货地址")
    @PostMapping("/addUserAddress")
    public Result addUserAddress(@RequestBody NbmhUserAddress userAddress){
        boolean ret = addressService.save(userAddress);
        if(ret){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(description = "修改用户收货地址")
    @PostMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestBody NbmhUserAddress userAddress){
        boolean ret = addressService.updateById(userAddress);
        if(ret){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }
}
