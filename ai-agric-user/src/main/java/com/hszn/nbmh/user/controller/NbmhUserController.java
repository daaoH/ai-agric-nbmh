package com.hszn.nbmh.user.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.CodeImageRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserCredentials;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.feign.RemoteThirdService;
import com.hszn.nbmh.user.api.params.input.RegisterParam;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import com.hszn.nbmh.user.service.INbmhUserCredentialsService;
import com.hszn.nbmh.user.service.INbmhUserExtraInfoService;
import com.hszn.nbmh.user.service.INbmhUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户基本表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-15
 */
@Api(tags = "用户接口")
@RestController
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RequiredArgsConstructor
@RequestMapping("/nbmh-user")
public class NbmhUserController {

    private final INbmhUserService userService;

    private final INbmhUserExtraInfoService extraInfoService;

    private final INbmhUserCredentialsService userCredentialsService;

    private final RemoteThirdService thirdService;

    SnowFlakeIdUtil snowFlakeId = new SnowFlakeIdUtil(1L, 1L);

    @Inner(false)
    @ApiOperation("根据用户名查询用户信息")
    @ApiImplicitParam(name = "phone", value = "手机号")
    @PostMapping("/queryUserByPhone")
    public Result<LoginUser> queryUserByPhone(@RequestParam("phone") String phone) {
        LoginUser loginUser = new LoginUser();
        NbmhUser user = userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, phone));
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
        }

        loginUser.setUser(user);
        List<NbmhUserExtraInfo> extraInfo = extraInfoService.list(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, user.getId()));
        loginUser.setExtraInfo(extraInfo);
        return Result.ok(loginUser);
    }

    @Inner(false)
    @ApiOperation("查询用户名是否存在")
    @ApiImplicitParam(name = "userName", value = "用户名(手机号)")
    @PostMapping("/checkUserExist")
    public Result checkUserExist(@RequestParam("userName") String userName, @RequestHeader(SecurityConstants.FROM) String from) {
        NbmhUserCredentials userCredentials = userCredentialsService.queryByUsername(userName);
        if (ObjectUtils.isEmpty(userCredentials)) {
            return Result.ok(false);
        }
        return Result.ok(true);
    }

    @Inner(false)
    @Operation(summary = "查询当前用户信息")
    @Parameter(description = "type 类型 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家")
    @PostMapping("/queryCurUserInfo")
    public Result<CurUserInfo> queryCurUserInfo(@RequestParam("userId") Long userId, @RequestParam(value = "type", required = false) Integer type) {
        CurUserInfo curUser = new CurUserInfo();
        NbmhUser nbmhUser = userService.getById(userId);
        curUser.setUser(nbmhUser);
        List<Integer> roles = new ArrayList<>();
        if (ObjectUtils.isEmpty(type)) {
            List<NbmhUserExtraInfo> extraInfos = extraInfoService.list(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, userId).eq(NbmhUserExtraInfo::getStatus, 0));
            if (CollectionUtils.isNotEmpty(extraInfos)) {
                if (extraInfos.size() > 1) {
                    curUser.setMutilRole(true);
                    roles = extraInfos.stream().map(e -> e.getType()).collect(Collectors.toList());
                } else {
                    curUser.setMutilRole(false);
                    curUser.setType(type);
                    curUser.setExtraInfo(extraInfos.get(0));
                    roles.add(type);
                }
            }
        } else {
            NbmhUserExtraInfo extraInfo = extraInfoService.getOne(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, userId).eq(NbmhUserExtraInfo::getStatus, 0).eq(NbmhUserExtraInfo::getType, type));
            curUser.setExtraInfo(extraInfo);
            curUser.setType(type);
            curUser.setMutilRole(false);
            roles.add(type);
        }
        curUser.setRoles(roles);
        return Result.ok(curUser);
    }

    @Inner(false)
    @Transactional
    @Operation(summary = "注册用户基础信息")
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody RegisterParam param) {
        String userName = param.getUserName();
        Integer loginType = param.getLoginType();

        NbmhUser user = this.assembleUserInfo(userName);
        boolean userSave = userService.save(user);
        if (!userSave) {
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        }
        NbmhUserCredentials userCredentials = new NbmhUserCredentials();
        userCredentials.setUserName(userName);
        userCredentials.setType(loginType);
        userCredentials.setUserId(user.getId());
        boolean credentialSave = userCredentialsService.save(userCredentials);
        if (!credentialSave) {
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        }

        return Result.ok();
    }


    @Transactional
    @Operation(summary = "防疫站-站长注册")
    @PostMapping("/stationMasterRegister")
    public Result stationMasterRegister(@RequestBody RegisterParam param) {
        if (ObjectUtils.isEmpty(param.getUserName()) || ObjectUtils.isEmpty(param.getExtraInfo())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //获取用户用基础信息
        NbmhUser user = userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getUserName()));
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed("为获取到当前和用户信息!请先注册账号!谢谢!");
        }
        //生成二维码基本参数
        param.getExtraInfo().setQrcode(this.generateQrCode(user.getId()));
        //标识站长最上级 为0
        param.getExtraInfo().setParentId(0L);
        /**
         * 校验用户附属信息
         */
        NbmhUserExtraInfo userExtraInfo = this.getUserExtraInfo(param.getInviteType(), user.getId());
        if (ObjectUtils.isEmpty(userExtraInfo)) {
            NbmhUserExtraInfo extraInfo = this.assembleUserExtraInfo(param.getExtraInfo());
            extraInfo.setUserId(user.getId());
            extraInfo.setType(3);//类型站长
            if (!extraInfoService.save(extraInfo)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
        } else {
            userExtraInfo.setStatus(0);
            extraInfoService.updateById(userExtraInfo);
        }
        return Result.ok();
    }


    @Transactional
    @Operation(summary = "防疫站-注册旗下人员")
    @PostMapping("/psRegisterUser")
    public Result preventStationRegisterUser(@RequestBody RegisterParam param) {
        if (ObjectUtils.isEmpty(param.getUserName()) || ObjectUtils.isEmpty(param.getExtraInfo())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //检验用户是否存在
        NbmhUserCredentials checkedUserCredentials = userCredentialsService.queryByUsername(param.getUserName());
        if (ObjectUtils.isEmpty(checkedUserCredentials)) {
            //不存在则创建+需给上级分配积分奖励
            NbmhUser user = this.assembleUserInfo(param.getUserName());
            user.setPhone(param.getUserName());
            if (!userService.save(user)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
            NbmhUserCredentials userCredentials = new NbmhUserCredentials();
            userCredentials.setUserName(param.getUserName());
            userCredentials.setType(2);//登录类型-手机
            userCredentials.setUserId(user.getId());
            if (!userCredentialsService.save(userCredentials)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
            //生成二维码基本参数
            param.getExtraInfo().setQrcode(this.generateQrCode(user.getId()));
            NbmhUserExtraInfo extraInfo = this.assembleUserExtraInfo(param.getExtraInfo());
            extraInfo.setUserId(user.getId());
            if (!extraInfoService.save(extraInfo)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }

            if (param.getInviteType() == 2) {
                //TODO 用户积分-分配
                Result configData = thirdService.getBySubject("invite");
                configData.getData();

            }
        } else {
            //存在则解锁原账号状态
            NbmhUser user = userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getUserName()));
            user.setStatus(0);
            if (!userService.updateById(user)) {
                return Result.failed(CommonEnum.FAILED_RESPONSE.getMsg());
            }
            /**
             * 校验用户附属信息
             */
            NbmhUserExtraInfo userExtraInfo =  this.getUserExtraInfo(param.getInviteType(), user.getId());
            if (ObjectUtils.isEmpty(userExtraInfo)) {
                NbmhUserExtraInfo extraInfo = this.assembleUserExtraInfo(param.getExtraInfo());
                extraInfo.setUserId(user.getId());
                extraInfo.setType(3);//类型站长
                if (!extraInfoService.save(extraInfo)) {
                    return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
                }
            } else {
                userExtraInfo.setStatus(0);
                extraInfoService.updateById(userExtraInfo);
            }
        }

        return Result.ok();
    }



    @Transactional
    @Operation(summary="防疫站站长-修改旗下人员")
    @PutMapping("/psUpdateUser")
    public Result psUpdateUser(@RequestBody NbmhUserExtraInfo param) {
        if (ObjectUtils.isEmpty(param)) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //存在则解锁原账号状态
        NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getId, param.getUserId()));
        NbmhUserExtraInfo userExtraInfo=extraInfoService.getById(param.getId());
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(userExtraInfo)) {
            return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
        }
        extraInfoService.updateById(userExtraInfo);
        return Result.ok();
    }


    @Transactional
    @Operation(summary="防疫站站长删除旗下人员")
    @PutMapping("/psDeleteUser")
    public Result psDeleteUser(@RequestBody RegisterParam param) {
        if (ObjectUtils.isEmpty(param.getUserName()) || ObjectUtils.isEmpty(param.getExtraInfo())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //TODO 积分清空
        //TODO 删除对应角色
        //TODO 清空附属

        //存在则解锁原账号状态
        NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getUserName()));
        NbmhUserExtraInfo userExtraInfo=extraInfoService.getById(param.getExtraInfo().getId());
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(userExtraInfo)) {
            return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
        }
        extraInfoService.updateById(userExtraInfo);
        return Result.ok();
    }


    /**
     * 检验用户是否存在
     *
     * @param userName
     * @return
     */
    public boolean checkUser(String userName) {
        if (ObjectUtils.isEmpty(userCredentialsService.queryByUsername(userName))) {
            return false;
        }
        return true;
    }

    /**
     * 组装用户信息
     *
     * @param userName
     * @return
     */
    public NbmhUser assembleUserInfo(String userName) {
        NbmhUser user = new NbmhUser();
        user.setId(snowFlakeId.nextId());
        user.setUserName(userName);
        user.setStatus(0);
        user.setBalance(BigDecimal.ZERO);
        user.setCoin(0);
        user.setIntegral(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return user;
    }

    /**
     * 组装用户附属信息(处理信息转换对象)
     *
     * @param extraInfo
     * @return
     */
    public NbmhUserExtraInfo assembleUserExtraInfo(NbmhUserExtraInfo extraInfo) {
        NbmhUserExtraInfo newSerExtraInfo = new NbmhUserExtraInfo();
        BeanUtils.copyProperties(extraInfo, newSerExtraInfo);
        newSerExtraInfo.setCreateTime(new Date());
        newSerExtraInfo.setAuthStatus(2);
        newSerExtraInfo.setStatus(0);
        return newSerExtraInfo;
    }

    /**
     * 生成二维码基本参数(远程调用)
     *
     * @param userId
     * @return
     */
    public String generateQrCode(Long userId) {
        CodeImageRequest imageRequest = new CodeImageRequest();
        imageRequest.setType(1);
        //TODO 生成参数待定
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("userId", userId);
        imageRequest.setContent(JSON.toJSONString(objectMap));
        Result codeImageResult = thirdService.generate(imageRequest);
        return String.valueOf(codeImageResult.getData());
    }


    /**
     * 防疫拉新(新养殖户)积分获取处理
     * 获取积分规则(远程调用)
     *
     * @param userId
     * @return
     */
    public void integralAdd(Long userId) {
        //积分规则比例(业务类型)
        String invite = "invite";
        //获取积分规则
        Result configData = thirdService.getBySubject(invite);
        configData.getData();

    }


    /**
     * 校验附属信息是否存在
     *
     * @param userId
     * @param type
     */
    private NbmhUserExtraInfo getUserExtraInfo(int type, Long userId) {
        //添加条件
        LambdaQueryWrapper<NbmhUserExtraInfo> queryWrapper = new LambdaQueryWrapper<>();
        //类型
        queryWrapper.eq(NbmhUserExtraInfo::getType, type);
        //用户id
        queryWrapper.eq(NbmhUserExtraInfo::getUserId, userId);

        return extraInfoService.getOne(queryWrapper);
    }

}
