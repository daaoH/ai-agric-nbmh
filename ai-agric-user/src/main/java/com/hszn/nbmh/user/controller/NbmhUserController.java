package com.hszn.nbmh.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.mould.CodeImageRequest;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.feign.RemoteNbmhFarmService;
import com.hszn.nbmh.prevent.api.feign.RemotePreventStationService;
import com.hszn.nbmh.prevent.api.feign.RemoteUserIntegralService;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.hszn.nbmh.third.feign.RemoteBaseConfigService;
import com.hszn.nbmh.third.feign.RemoteCodeImageService;
import com.hszn.nbmh.user.api.entity.*;
import com.hszn.nbmh.user.api.feign.RemotePreventService;
import com.hszn.nbmh.user.api.params.input.*;
import com.hszn.nbmh.user.api.params.out.AnimalDoctorInfo;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import com.hszn.nbmh.user.service.*;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.spatial4j.core.shape.Rectangle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 *
 * @author yuan
 * @since 2022-08-15
 */
@Api(tags="????????????")
@RestController
@Validated
@SecurityRequirement(name=HttpHeaders.AUTHORIZATION)
@RequiredArgsConstructor
@RequestMapping("/nbmh-user")
public class NbmhUserController {

    private final INbmhUserService userService;

    private final INbmhUserExtraInfoService extraInfoService;

    private final INbmhAnimalDoctorDetailService animalDoctorDetailService;

    private final INbmhUserCredentialsService userCredentialsService;

    private final RemotePreventStationService preventStationService;
    private final RemoteCodeImageService codeImageService;

    private final RemotePreventService remotePreventService;

    private final RemoteUserIntegralService userIntegralService;

    private final RemoteBaseConfigService baseConfigService;


    private final RemoteNbmhFarmService farmService;

    //???????????????????????????
    private final INbmhUserCoinRecordService coinRecordService;

    SnowFlakeIdUtil snowFlakeId=new SnowFlakeIdUtil(1L, 1L);

    private final SpatialContext spatialContext=SpatialContext.GEO;

    private static final String GEO_CODE="geo_code";

    private static final int LEN=3;

    private static Lock lock=new ReentrantLock();

    @Inner(false)
    @ApiOperation("?????????????????????????????????")
    @ApiImplicitParam(name="phone", value="?????????")
    @PostMapping("/queryUserByPhone")
    public Result<LoginUser> queryUserByPhone(@RequestParam("phone") String phone) {
        LoginUser loginUser=new LoginUser();
        NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, phone));
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
        }

        loginUser.setUser(user);
        List<NbmhUserExtraInfo> extraInfo=extraInfoService.list(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, user.getId()).eq(NbmhUserExtraInfo::getStatus, 0));
        loginUser.setExtraInfo(extraInfo);
        return Result.ok(loginUser);
    }

    @Inner(false)
    @ApiOperation("???????????????????????????")
    @ApiImplicitParam(name="userName", value="?????????(?????????)")
    @PostMapping("/checkUserExist")
    public Result checkUserExist(@RequestParam("userName") String userName, @RequestHeader(SecurityConstants.FROM) String from) {
        NbmhUserCredentials userCredentials=userCredentialsService.queryByUsername(userName);
        if (ObjectUtils.isEmpty(userCredentials)) {
            return Result.ok(false);
        }
        return Result.ok(true);
    }

    @Inner(false)
    @Operation(summary="????????????????????????")
    @Parameter(description="type ?????? 1???????????? 2?????? 3?????? 4????????? 5????????? 6??????")
    @PostMapping("/queryCurUserInfo")
    public Result<CurUserInfo> queryCurUserInfo(@RequestParam("userId") Long userId, @RequestParam(value="type", required=false) Integer type) {
        CurUserInfo curUser=new CurUserInfo();
        NbmhUser nbmhUser=userService.getById(userId);
        curUser.setUser(nbmhUser);
        List<Integer> roles=new ArrayList<>();
        if (ObjectUtils.isEmpty(type)) {
            List<NbmhUserExtraInfo> extraInfos=extraInfoService.list(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, userId).eq(NbmhUserExtraInfo::getStatus, 0));
            if (CollectionUtils.isNotEmpty(extraInfos)) {
                if (extraInfos.size() > 1) {
                    curUser.setMutilRole(true);
                    roles=extraInfos.stream().map(e -> e.getType()).collect(Collectors.toList());
                } else {
                    curUser.setMutilRole(false);
                    curUser.setType(type);
                    curUser.setExtraInfo(extraInfos.get(0));
                    roles.add(type);
                }
            }
        } else {
            NbmhUserExtraInfo extraInfo=extraInfoService.getOne(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getUserId, userId).eq(NbmhUserExtraInfo::getStatus, 0).eq(NbmhUserExtraInfo::getType, type));
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
    @Operation(summary="????????????????????????")
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody RegisterParam param) {
        String userName=param.getUserName();
        Integer loginType=param.getLoginType();

        NbmhUser user=this.assembleUserInfo(userName);
        if (!ObjectUtils.isEmpty(param.getPhone())) {
            user.setPhone(param.getPhone());
        }
        //????????????????????????
        boolean userSave=userService.save(user);
        if (!userSave) {
            return Result.failed("????????????????????????");
        }
        //??????????????????
        NbmhUserCredentials userCredentials=new NbmhUserCredentials();
        userCredentials.setUserName(userName);
        userCredentials.setType(loginType);
        userCredentials.setUserId(user.getId());
        boolean credentialSave=userCredentialsService.save(userCredentials);
        if (!credentialSave) {
            return Result.failed("????????????????????????????????????");
        }

        //??????????????????
        NbmhUserExtraInfo extraInfo=new NbmhUserExtraInfo();
        extraInfo.setId(snowFlakeId.nextId());
        extraInfo.setUserId(user.getId());
        extraInfo.setStatus(0);
        extraInfo.setType(1);
        extraInfo.setCreateTime(new Date());
        extraInfo.setUpdateTime(new Date());
        boolean extraSave=extraInfoService.save(extraInfo);
        if (!extraSave) {
            return Result.failed("??????????????????????????????");
        }

        return Result.ok();
    }


    @Inner(false)
    @Transactional
    @Operation(summary="??????????????????")
    @PostMapping("/integralUpdate")
    public Result integralUpdate(@RequestBody NbmhUser param) {
        NbmhUser user=new NbmhUser();
        user.setIntegral(param.getIntegral());
        user.setId(param.getId());
        //???????????????
        boolean res=userService.updateById(user);
        if (res) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }


    @Transactional
    @Operation(summary="?????????-????????????")
    @PostMapping("/stationMasterRegister")
    public Result stationMasterRegister(@RequestBody RegisterParam param) {
        if (ObjectUtils.isEmpty(param.getUserName()) || ObjectUtils.isEmpty(param.getExtraInfo())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }

        //???????????????????????????
        NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getUserName()));
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed("?????????????????????????????????!??????????????????!??????!");
        }
        //???????????????????????????
        param.getExtraInfo().setQrcode(this.generateQrCode(user.getId()));
        //????????????????????? ???0
        param.getExtraInfo().setParentId(0L);
        /**
         * ????????????????????????
         */
        NbmhUserExtraInfo userExtraInfo=this.getUserExtraInfo(param.getInviteType(), user.getId());
        if (ObjectUtils.isEmpty(userExtraInfo)) {
            NbmhUserExtraInfo extraInfo=this.assembleUserExtraInfo(param.getExtraInfo());
            extraInfo.setUserId(user.getId());
            extraInfo.setAuthStatus(1);//???????????????
            extraInfo.setStatus(0);
            extraInfo.setType(3);//????????????
            if (!extraInfoService.save(extraInfo)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
            NbmhPreventStationParam preventStationParam=new NbmhPreventStationParam();
            preventStationParam.setStationName(param.getPreventStationName());
            preventStationParam.setMasterId(user.getId());
            preventStationParam.setStatus(-1);
            preventStationParam.setStationMaster(param.getUserName());
            preventStationParam.setStationPhone(param.getUserName());
            preventStationParam.setCreateTime(new Date());
            preventStationParam.setCertificate(param.getExtraInfo().getCertificate());
            remotePreventService.add(preventStationParam);
        } else {
            userExtraInfo.setAuthStatus(1);//???????????????
            userExtraInfo.setStatus(-1);
            extraInfoService.updateById(userExtraInfo);
        }
        return Result.ok();
    }

    @Inner(false)
    @Operation(summary="????????????????????????")
    @Parameter(description="type ?????? 1???????????? 2?????? 3?????? 4????????? 5????????? 6??????")
    @PostMapping("/getUserInfoCount")
    public Result getUserInfoCount(@RequestParam("preventStationId") Long preventStationId, @RequestParam(value="type") Integer type) {
        return Result.ok(extraInfoService.count(Wrappers.<NbmhUserExtraInfo>query().lambda().eq(NbmhUserExtraInfo::getPreventStationId, preventStationId).eq(NbmhUserExtraInfo::getStatus, 0).eq(NbmhUserExtraInfo::getType, type)));
    }


    @Transactional
    @Operation(summary="?????????-??????????????????")
    @PostMapping("/psRegisterUser")
    public Result preventStationRegisterUser(@RequestBody RegisterParam param) {
        if (ObjectUtils.isEmpty(param.getUserName()) || ObjectUtils.isEmpty(param.getExtraInfo())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //????????????????????????
        NbmhUserCredentials checkedUserCredentials=userCredentialsService.queryByUsername(param.getUserName());
        if (ObjectUtils.isEmpty(checkedUserCredentials)) {
            //??????????????????+??????????????????????????????
            NbmhUser user=this.assembleUserInfo(param.getUserName());
            user.setPhone(param.getUserName());
            if (!userService.save(user)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
            NbmhUserCredentials userCredentials=new NbmhUserCredentials();
            userCredentials.setUserName(param.getUserName());
            userCredentials.setType(2);//????????????-??????
            userCredentials.setUserId(user.getId());
            if (!userCredentialsService.save(userCredentials)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }
            //???????????????????????????
            param.getExtraInfo().setQrcode(this.generateQrCode(user.getId()));
            NbmhUserExtraInfo extraInfo=this.assembleUserExtraInfo(param.getExtraInfo());
            extraInfo.setType(param.getInviteType());
            extraInfo.setAuthStatus(2);//???????????????
            extraInfo.setStatus(0);
            extraInfo.setUserId(user.getId());
            if (!extraInfoService.save(extraInfo)) {
                return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
            }

            if (param.getInviteType() == 5) {
                /**
                 * ???????????????(???????????????--??????)
                 */
                if (!ObjectUtils.isEmpty(param.getFarmParams())) {
                    List<NbmhFarm> farms=new ArrayList<>();
                    param.getFarmParams().forEach(f -> {
                        NbmhFarm nbmhFarm=new NbmhFarm();
                        nbmhFarm.setStatus(0);
                        nbmhFarm.setCreateTime(new Date());
                        nbmhFarm.setFarmAddress(f.getFarmAddress());
                        nbmhFarm.setFarmAnimalJson(f.getFarmAnimalJson());
                        nbmhFarm.setFarmName(f.getFarmName());
                        nbmhFarm.setFarmerId(user.getId());
                        nbmhFarm.setFarmScale(f.getFarmScale());
                        nbmhFarm.setManageYear(f.getManageYear());
                        nbmhFarm.setPreventStationId(f.getPreventStationId());
                        nbmhFarm.setUserId(f.getUserId());
                        nbmhFarm.setUserName(f.getUserName());
                        farms.add(nbmhFarm);
                    });
                    farmService.submitList(farms);
                }

                //??????????????????-??????
                Result configData=baseConfigService.getBySubject("invite");
                List<NbmhBaseConfig> configs=JSON.parseArray(JSON.toJSONString(configData.getData()), NbmhBaseConfig.class);
                int isOpen=0;
                BigDecimal leaderRatio=new BigDecimal("0.00");
                BigDecimal staffRatio=new BigDecimal("0.00");
                BigDecimal rewardAmount=new BigDecimal("0.00");

                for (NbmhBaseConfig baseConfig : configs) {
                    if ("staff_ratio".equals(baseConfig.getConfigKey())) {
                        staffRatio=new BigDecimal(baseConfig.getConfigValue()).divide(new BigDecimal(10));//?????????????????????10??????????????????
                    } else if ("reward_amount".equals(baseConfig.getConfigKey())) {
                        rewardAmount=new BigDecimal(baseConfig.getConfigValue());
                    } else if ("is_open".equals(baseConfig.getConfigKey())) {
                        isOpen=Integer.parseInt(baseConfig.getConfigValue());
                    } else {
                        leaderRatio=new BigDecimal(baseConfig.getConfigValue());
                    }
                }
                if (isOpen == 1) {
                    //??????????????????(??????????????????)
                    List<NbmhUserIntegralRecord> userIntegralRecords=new ArrayList<>();
                    //??????????????????-??????????????????
                    if (param.isStationMaster()) {
                        //??????????????????
                        NbmhUser stationMaster=userService.getById(param.getExtraInfo().getUserId());
                        userIntegralRecords.add(
                                NbmhUserIntegralRecord.builder()
                                        .userId(stationMaster.getId())
                                        .userName(!ObjectUtils.isEmpty(param.getExtraInfo().getRealName()) ? param.getExtraInfo().getRealName() : "")
                                        .userAvatarUrl("")
                                        .vaccinId(stationMaster.getId())
                                        .vaccinUser(stationMaster.getUserName())
                                        .vaccinAvatarUrl(!ObjectUtils.isEmpty(stationMaster.getAvatarUrl()) ? stationMaster.getAvatarUrl() : "")
                                        .source(2).integral(rewardAmount.intValue()).status(0).createTime(new Date()).isIncome(1).build());
                        stationMaster.setIntegral(stationMaster.getIntegral() + rewardAmount.intValue());
                        userService.updateById(stationMaster);
                    } else {
                        List<NbmhUser> NbmhUserList=new ArrayList<>();

                        //?????????
                        NbmhUser preventOfficer=userService.getById(param.getInviteBy());
                        //?????????????????????
                        NbmhUserExtraInfo userExtraInfo=this.getUserExtraInfo(4, param.getInviteBy());
                        //??????????????????
                        NbmhUser stationMaster=userService.getById(userExtraInfo.getParentId());

                        //????????????????????? ????????????
                        int newStaffRatio=rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                        preventOfficer.setIntegral(preventOfficer.getIntegral() + newStaffRatio);
                        NbmhUserList.add(preventOfficer);
                        //?????????????????????
                        userIntegralRecords.add(
                                NbmhUserIntegralRecord.builder()
                                        .userId(user.getId())
                                        .userName(!ObjectUtils.isEmpty(param.getExtraInfo().getRealName()) ? param.getExtraInfo().getRealName() : "")
                                        .userAvatarUrl("")
                                        .vaccinId(preventOfficer.getId())
                                        .vaccinUser(preventOfficer.getUserName())
                                        .vaccinAvatarUrl(!ObjectUtils.isEmpty(preventOfficer.getAvatarUrl()) ? stationMaster.getAvatarUrl() : "")
                                        .source(1).integral(newStaffRatio).status(0).createTime(new Date()).isIncome(1).build());
                        //??????????????????
                        int stationMasterIntegral=rewardAmount.subtract(rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
                        //??????????????????
                        userIntegralRecords.add(NbmhUserIntegralRecord.builder()
                                .userId(user.getId())
                                .userName(!ObjectUtils.isEmpty(param.getExtraInfo().getRealName()) ? param.getExtraInfo().getRealName() : "")
                                .userAvatarUrl("")
                                .vaccinId(stationMaster.getId())
                                .vaccinUser(stationMaster.getUserName())
                                .vaccinAvatarUrl(!ObjectUtils.isEmpty(stationMaster.getAvatarUrl()) ? stationMaster.getAvatarUrl() : "")
                                .source(4).integral(stationMasterIntegral).status(0).createTime(new Date()).isIncome(1).build());

                        stationMaster.setIntegral(stationMaster.getIntegral() + stationMasterIntegral);
                        NbmhUserList.add(stationMaster);
                        userService.updateBatchById(NbmhUserList);
                    }
                    if (!ObjectUtils.isEmpty(userIntegralRecords)) {
                        userIntegralService.submit(userIntegralRecords);
                    }
                }
            }
        } else {
            //??????????????????????????????
            NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getUserName()));
            user.setStatus(0);
            if (!userService.updateById(user)) {
                return Result.failed(CommonEnum.FAILED_RESPONSE.getMsg());
            }
            /**
             * ????????????????????????
             */
            NbmhUserExtraInfo userExtraInfo=this.getUserExtraInfo(param.getInviteType(), user.getId());
            if (ObjectUtils.isEmpty(userExtraInfo)) {
                NbmhUserExtraInfo extraInfo=this.assembleUserExtraInfo(param.getExtraInfo());
                extraInfo.setUserId(user.getId());
                extraInfo.setType(3);//????????????
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
    @Operation(summary="???????????????-??????????????????(??????????????????)")
    @PostMapping("/psUpdateUser")
    public Result psUpdateUser(@RequestBody NbmhUserExtraInfo param) {
        boolean res=extraInfoService.updateById(param);
        if (res) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }


    @Transactional
    @Operation(summary="?????????????????????????????????")
    @PostMapping("/psDeleteUser")
    public Result psDeleteUser(@RequestBody NbmhUserExtraInfo param) {
        if (ObjectUtils.isEmpty(param)) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }
        //??????????????????????????????
        NbmhUser user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getId, param.getUserId()));
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
        }
        //????????????
        user.setIntegral(0);
        userService.updateById(user);
        //????????????????????????
        NbmhUserExtraInfo userExtraInfo=extraInfoService.getById(param.getId());
        if (ObjectUtils.isEmpty(userExtraInfo)) {
            return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
        } else {
            userExtraInfo.setStatus(-1);
            extraInfoService.updateById(userExtraInfo);
        }
        return Result.ok();
    }

    @Inner(false)
    @Operation(summary="????????????????????????????????????")
    @PostMapping("/queryBreederList")
    public Result queryBreederList(@RequestBody QueryRequest<UserPageParam> param) {
        //???????????????
        List<CurUserInfo> curUserInfoList=new ArrayList<>();
        //??????????????????
        LambdaQueryWrapper<NbmhUserExtraInfo> queryWrapper=new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(param.getQueryEntity())) {
            //?????????id
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getPreventStationId())) {
                queryWrapper.eq(NbmhUserExtraInfo::getPreventStationId, param.getQueryEntity().getPreventStationId());
            }
            //????????????
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getUserName())) {
                queryWrapper.like(NbmhUserExtraInfo::getRealName, param.getQueryEntity().getUserName());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getUserAddress())) {
                queryWrapper.like(NbmhUserExtraInfo::getAddress, param.getQueryEntity().getUserAddress());
            }
            //???????????? 4?????????+????????? 5?????????
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getType()) && param.getQueryEntity().getType() == 4) {
                List<Integer> types=new ArrayList();
                types.add(4);
                types.add(7);
                queryWrapper.in(NbmhUserExtraInfo::getType, types);
            } else if (!ObjectUtils.isEmpty(param.getQueryEntity().getType())) {
                queryWrapper.eq(NbmhUserExtraInfo::getType, param.getQueryEntity().getType());
            }
        }
        //??????
        Page<NbmhUserExtraInfo> page=new Page<>(param.getPageNum(), param.getPageSize());
        //??????
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        //???????????????
        IPage<NbmhUserExtraInfo> extraInfos=extraInfoService.page(page, queryWrapper);
        List<Long> ids=new ArrayList<>();
        if (!ObjectUtils.isEmpty(extraInfos.getRecords())) {
            extraInfos.getRecords().forEach(ex -> {
                ids.add(ex.getUserId());
            });
            List<NbmhUser> userLsit=userService.listByIds(ids);
            userLsit.forEach(u -> {
                extraInfos.getRecords().forEach(ex -> {
                    if (u.getId().equals(ex.getUserId())) {
                        CurUserInfo userInfo=new CurUserInfo();
                        userInfo.setUser(u);
                        userInfo.setExtraInfo(ex);
                        curUserInfoList.add(userInfo);
                    }
                });
            });
        }
        return Result.ok(curUserInfoList);
    }

    @Transactional
    @Operation(summary="??????????????????")
    @PostMapping("/animalDoctorRegister")
    @Inner(value=false)
    public Result animalDoctorRegister(@RequestBody AnimalDoctorRegisterParam param) {

        if (ObjectUtils.isEmpty(param.getLoginName()) || ObjectUtils.isEmpty(param.getExtraInfo()) || ObjectUtils.isEmpty(param.getAnimalDoctorDetail())) {
            return Result.failed(CommonEnum.PARAM_MISS.getMsg());
        }

        NbmhUser user;
        if (param.getLoginType() == 0) {
            user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getUserName, param.getLoginName()));
        } else {
            user=userService.getOne(Wrappers.<NbmhUser>query().lambda().eq(NbmhUser::getPhone, param.getLoginName()));
        }

        if (ObjectUtils.isEmpty(user)) {
            return Result.failed("??????????????????????????????!??????????????????!??????!");
        }

        param.getExtraInfo().setParentId(0L).setQrcode(this.generateQrCode(user.getId()));

        NbmhUserExtraInfo userExtraInfo=this.getUserExtraInfo(param.getExtraInfo().getType(), user.getId());
        if (ObjectUtils.isEmpty(userExtraInfo)) {

            //????????????????????????
            extraInfoService.save(param.getExtraInfo().setUserId(user.getId()).setType(2).setCreateTime(new Date()).setAuthStatus(2).setStatus(0));
        } else {

            //????????????????????????
            extraInfoService.updateById(param.getExtraInfo().setId(userExtraInfo.getId()).setStatus(0).setUpdateTime(new Date()));
        }

        NbmhAnimalDoctorDetail animalDoctorDetail=this.getAnimalDoctorDetail(user.getId());
        if (ObjectUtils.isEmpty(animalDoctorDetail)) {

            //????????????????????????
            animalDoctorDetailService.save(param.getAnimalDoctorDetail().setUserId(user.getId()).setCreateTime(new Date()).setStatus(0));
        } else {

            //????????????????????????
            animalDoctorDetailService.updateById(param.getAnimalDoctorDetail().setId(animalDoctorDetail.getId()).setStatus(0).setUpdateTime(new Date()));
        }

        return Result.ok();
    }

    @Operation(summary="????????????Id????????????????????????", method="POST")
    @PostMapping("/getAnimalDoctorInfoByUserId")
    @Inner(value=false)
    public Result<AnimalDoctorInfo> getAnimalDoctorInfoByUserId(@RequestParam(value="userId") @NotNull(message="??????????????????userId????????????") Long userId) {
        NbmhUser user=userService.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed("????????????????????????????????????????????????????????????");
        }

        AnimalDoctorInfo animalDoctorInfo=AnimalDoctorInfo.builder().build();
        BeanUtils.copyProperties(user, animalDoctorInfo);

        NbmhUserExtraInfo userExtraInfo=extraInfoService.getOne(Wrappers.query(NbmhUserExtraInfo.builder().userId(userId).type(2).build()));
        if (!ObjectUtils.isEmpty(userExtraInfo)) {
            BeanUtils.copyProperties(userExtraInfo, animalDoctorInfo);

            if (userExtraInfo.getPreventStationId() != null) {
                Result<NbmhPreventStation> ret=preventStationService.getById(userExtraInfo.getPreventStationId());
                NbmhPreventStation preventStation=ret.getData();
                if (!ObjectUtils.isEmpty(preventStation)) {
                    animalDoctorInfo.setPreventStationName(preventStation.getStationName());
                }
            }
        }

        NbmhAnimalDoctorDetail doctorDetail=animalDoctorDetailService.getOne(Wrappers.query(NbmhAnimalDoctorDetail.builder().userId(userId).build()));
        if (!ObjectUtils.isEmpty(doctorDetail)) {
            BeanUtils.copyProperties(doctorDetail, animalDoctorInfo);
        }

        return Result.ok(animalDoctorInfo);
    }

    @Transactional
    @Operation(summary="??????????????????????????????")
    @PostMapping("/animalDoctorUpdate")
    @Inner(value=false)
    public Result animalDoctorUpdate(@RequestBody @Validated({AnimalDoctorUpdateParam.Update.class}) AnimalDoctorUpdateParam param) {

        NbmhUser user=userService.getById(param.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            return Result.failed("????????????????????????????????????????????????????????????");
        }

        //????????????????????????
        extraInfoService.updateById(param.getExtraInfo());

        //????????????????????????
        animalDoctorDetailService.updateById(param.getAnimalDoctorDetail());

        return Result.ok();
    }

    @Operation(summary="????????????????????????", method="POST")
    @PostMapping("/animalDoctor/popular")
    @Inner(value=false)
    public Result<List<AnimalDoctorInfo>> popular(@RequestBody QueryCondition<NbmhAnimalDoctorDetail> queryCondition,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize,
                                                  @RequestParam(value="acceptOrderMinNum", required=false) Integer acceptOrderMinNum,
                                                  @RequestParam(value="acceptOrderMaxNum", required=false) Integer acceptOrderMaxNum) {

        List<OrderItem> orderItemList=queryCondition.getOrderItemList();
        if (CollectionUtils.isEmpty(orderItemList)) {
            orderItemList=new ArrayList<>();
        }
        OrderItem orderItem=new OrderItem();
        orderItem.setAsc(false);
        orderItem.setColumn("heat_weight");
        orderItemList.add(orderItem);

        IPage<NbmhAnimalDoctorDetail> animalDoctorDetailIPage=animalDoctorDetailService.query(queryCondition.getEntity(), pageNum, pageSize, acceptOrderMinNum, acceptOrderMaxNum, orderItemList);
        List<NbmhAnimalDoctorDetail> animalDoctorList=animalDoctorDetailIPage.getRecords();

        if (CollectionUtils.isEmpty(animalDoctorList)) {
            return Result.ok();
        }

        return Result.ok(buildAnimalDoctorInfo(animalDoctorList));
    }

    @Operation(summary="??????????????????????????????", method="POST")
    @Parameters({@Parameter(description="???????????? ??????km", name="distance"), @Parameter(description="????????????", name="userLng"), @Parameter(description="????????????", name="userLat")})
    @PostMapping("/animalDoctor/nearby")
    @Inner(value=false)
    public Result<List<AnimalDoctorInfo>> nearby(@RequestBody QueryCondition<NbmhAnimalDoctorDetail> queryCondition,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize,
                                                 @RequestParam(value="acceptOrderMinNum", required=false) Integer acceptOrderMinNum,
                                                 @RequestParam(value="acceptOrderMaxNum", required=false) Integer acceptOrderMaxNum,
                                                 @RequestParam("distance") double distance,
                                                 @RequestParam("userLng") double userLng,
                                                 @RequestParam("userLat") double userLat) {
        //1.?????????????????????
        Rectangle rectangle=getRectangle(distance, userLng, userLat);
        //2.??????????????????????????????????????????
        IPage<NbmhAnimalDoctorDetail> animalDoctorDetailIPage=animalDoctorDetailService.query(queryCondition.getEntity(), pageNum, pageSize, acceptOrderMinNum,
                acceptOrderMaxNum, rectangle.getMinX(), rectangle.getMaxX(), rectangle.getMinY(), rectangle.getMaxY(), queryCondition.getOrderItemList());

        List<NbmhAnimalDoctorDetail> animalDoctorList=animalDoctorDetailIPage.getRecords();

        if (CollectionUtils.isEmpty(animalDoctorList)) {
            return Result.ok();
        }
        //3.?????????????????????????????????????????????
        animalDoctorList=animalDoctorList.stream().filter(a -> getDistance(a.getLongitude(), a.getLatitude(), userLng, userLat) <= distance).collect(Collectors.toList());

        return Result.ok(buildAnimalDoctorInfo(animalDoctorList));
    }

    @Operation(summary="??????ID????????????????????????", method="GET")
    @Parameters({@Parameter(description="??????ID????????????????????????", name="id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhUser> getById(@PathVariable(value="id") @NotNull Long id) {

        return Result.ok(userService.getById(id));
    }

    @Transactional
    @Operation(summary="?????????????????????")
    @PostMapping("/coinUpdate")
    @Inner(false)
    public Result coinUpdate(@RequestBody CoinParam coinParam) {
        boolean res;
        try {
            lock.lock();
            NbmhUser farmer=userService.getById(coinParam.getUserId());
            if (ObjectUtils.isEmpty(farmer)) {
                throw new ServiceException("????????????????????????????????????????????????");
            }
            if (coinParam.getPayType() == 0) {
                if (coinParam.getPayMoney().compareTo(BigDecimal.valueOf(farmer.getCoin())) > 0) {
                    throw new ServiceException("?????????????????????????????????????????????");
                }
                res=userService.updateById(NbmhUser.builder().id(farmer.getId()).coin(farmer.getCoin() - coinParam.getPayMoney().intValue()).build());
            } else {
                res=userService.updateById(NbmhUser.builder().id(farmer.getId()).coin(farmer.getCoin() + coinParam.getPayMoney().intValue()).build());
            }
            coinRecordService.save(NbmhUserCoinRecord.builder()
                    .userId(coinParam.getUserId())
                    .bizType(coinParam.getBizType())
                    .bizId(coinParam.getBizId())
                    .createTime(new Date())
                    .status(0)
                    .isIncome(coinParam.getPayType())
                    .userName(farmer.getUserName()).build());
        } finally {
            lock.unlock();
        }
        if (res) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    private Rectangle getRectangle(double distance, double userLng, double userLat) {
        return spatialContext.getDistCalc().calcBoxByDistFromPt(spatialContext.makePoint(userLng, userLat),
                distance * DistanceUtils.KM_TO_DEG, spatialContext, null);
    }

    private List<AnimalDoctorInfo> buildAnimalDoctorInfo(List<NbmhAnimalDoctorDetail> animalDoctorList) {
        List<AnimalDoctorInfo> animalDoctorInfoList=new ArrayList<>();

        animalDoctorList.forEach(animalDoctor -> {
            //TODO: ????????????????????????????????????
            animalDoctor.setMedicalStatus(1);

            AnimalDoctorInfo animalDoctorInfo=AnimalDoctorInfo.builder().build();
            BeanUtils.copyProperties(animalDoctor, animalDoctorInfo);

            NbmhUser user=userService.getById(animalDoctor.getUserId());
            BeanUtils.copyProperties(user, animalDoctorInfo);

            NbmhUserExtraInfo userExtraInfo=extraInfoService.getOne(Wrappers.query(NbmhUserExtraInfo.builder().userId(animalDoctor.getUserId()).type(2).build()));
            BeanUtils.copyProperties(userExtraInfo, animalDoctorInfo);

            animalDoctorInfoList.add(animalDoctorInfo);
        });

        return animalDoctorInfoList;
    }

    /***
     * ??????????????????????????????
     * @param longitude ??????1
     * @param latitude  ??????1
     * @param userLng   ??????2
     * @param userLat   ??????2
     * @return ?????????????????????km
     */
    private double getDistance(Double longitude, Double latitude, double userLng, double userLat) {

        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat), spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }


    /**
     * ????????????????????????
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
     * ??????????????????
     *
     * @param userName
     * @return
     */
    public NbmhUser assembleUserInfo(String userName) {
        NbmhUser user=new NbmhUser();
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
     * ????????????????????????(????????????????????????)
     *
     * @param extraInfo
     * @return
     */
    public NbmhUserExtraInfo assembleUserExtraInfo(NbmhUserExtraInfo extraInfo) {
        NbmhUserExtraInfo newSerExtraInfo=new NbmhUserExtraInfo();
        BeanUtils.copyProperties(extraInfo, newSerExtraInfo);
        newSerExtraInfo.setCreateTime(new Date());
        return newSerExtraInfo;
    }

    /**
     * ???????????????????????????(????????????)
     *
     * @param userId
     * @return
     */
    public String generateQrCode(Long userId) {
        CodeImageRequest imageRequest=new CodeImageRequest();
        imageRequest.setType(1);
        //TODO ??????????????????
        Map<String, Object> objectMap=new HashMap<>();
        objectMap.put("userId", userId);
        imageRequest.setContent(JSON.toJSONString(objectMap));
        Result codeImageResult=codeImageService.generate(imageRequest);
        return String.valueOf(codeImageResult.getData());
    }

    /**
     * ??????????????????????????????
     *
     * @param userId
     * @param type
     */
    private NbmhUserExtraInfo getUserExtraInfo(int type, Long userId) {
        //????????????
        LambdaQueryWrapper<NbmhUserExtraInfo> queryWrapper=new LambdaQueryWrapper<>();
        //??????
        queryWrapper.eq(NbmhUserExtraInfo::getType, type);
        //??????id
        queryWrapper.eq(NbmhUserExtraInfo::getUserId, userId);

        return extraInfoService.getOne(queryWrapper);
    }

    /**
     * ????????????????????????????????????
     *
     * @param userId ????????????id
     */
    private NbmhAnimalDoctorDetail getAnimalDoctorDetail(Long userId) {
        //????????????
        LambdaQueryWrapper<NbmhAnimalDoctorDetail> queryWrapper=new LambdaQueryWrapper<>();
        //??????id
        queryWrapper.eq(NbmhAnimalDoctorDetail::getUserId, userId);

        return animalDoctorDetailService.getOne(queryWrapper);
    }

}
