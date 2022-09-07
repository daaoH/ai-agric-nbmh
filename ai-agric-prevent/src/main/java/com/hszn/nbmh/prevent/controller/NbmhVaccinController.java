package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.*;
import com.hszn.nbmh.prevent.api.params.input.PreventOfficerRecordParam;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.*;
import com.hszn.nbmh.prevent.service.*;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.hszn.nbmh.third.feign.RemoteBaseConfigService;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * <p>
 * 防疫信息表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-vaccin")
@Tag(name="防疫端接口")
public class NbmhVaccinController {

    //防疫接口
    private final INbmhVaccinService nbmhVaccinService;

    //检疫接口
    private final INbmhInspectService inspectService;
    //动物接口
    private final INbmhAnimalService nbmhAnimalService;

    //用户积分记录
    private final INbmhUserIntegralRecordService nbmhUserIntegralRecordService;

    //用户接口
    private final RemoteUserService userService;

    //举报接口
    private final INbmhCheckService checkService;

    //配置接口
    private final RemoteBaseConfigService baseConfigService;


    // 初始化
    SnowFlakeIdUtil idWorker=new SnowFlakeIdUtil(1, 0);

    @PostMapping("/submit")
    @Operation(summary="防疫员端-防疫登记")
    @Inner(false)
    public Result submit(@RequestBody List<VaccinParam> params) {
        //批量数据集合
        List<NbmhVaccin> vaccins=new ArrayList<>();
        //遍历处理数据
        for (VaccinParam param : params) {
            // 校验动物是否存在
            AnimalDetailsResult detailsResult=nbmhAnimalService.getByEarNo(param.getEarNo());
            if (ObjectUtils.isEmpty(detailsResult.getAnimal())) {
                // 移动端防疫信息登记同步创建动物信息
                Long id=this.nbmhAnimalService.insert(param);
                // 添加动物id
                param.setAnimalId(id);
            } else {
                // 添加动物id
                param.setAnimalId(detailsResult.getAnimal().getId());
            }
            // 生成防疫编号
            param.setVaccinNo(String.valueOf(idWorker.nextId()));
            // 添加创建时间
            param.setCreateTime(new Date());
            // 添加检疫时间
            param.setVaccinTime(new Date());
            //防疫信息组装
            vaccins.add(param);
        }
        // 提交防疫数据+补偿积分
        if (ObjectUtils.isNotEmpty(vaccins)) {
            boolean ret=this.nbmhVaccinService.saveBatch(vaccins);
            if (ret) {
                this.integralHandle(vaccins);
                return Result.ok();
            }
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }


    @PostMapping("/offLineSubmit")
    @Operation(summary="防疫员端-离线防疫登记")
    @Inner(false)
    public Result offLineSubmit(@RequestBody List<VaccinParam> params) {

        List<VaccinParam> resultAccinParams=new ArrayList<>();
        //批量数据集合
        List<NbmhVaccin> vaccins=new ArrayList<>();
        //遍历处理数据
        for (VaccinParam param : params) {
            // 校验动物是否存在
            AnimalDetailsResult detailsResult=nbmhAnimalService.getByEarNo(param.getEarNo());

            LoginUser loginUser=userService.getByPhone(param.getUserPhone()).getData();
            if (ObjectUtils.isEmpty(loginUser) ||
                    ObjectUtils.isEmpty(loginUser.getUser()) ||
                    ObjectUtils.isEmpty(loginUser.getExtraInfo())) {
                resultAccinParams.add(param);
                continue;
            }
            NbmhUserExtraInfo extraInfo=new NbmhUserExtraInfo();
            for (NbmhUserExtraInfo e : loginUser.getExtraInfo()) {
                if (e.getType() == 5) {
                    //对象转换
                    BeanUtils.copyProperties(e, extraInfo);
                    break;
                }
            }
            if (ObjectUtils.isEmpty(detailsResult.getAnimal())) {
                param.setUserId(loginUser.getUser().getId());
                param.setStatus(0);
                // 移动端防疫信息登记同步创建动物信息
                Long id=this.nbmhAnimalService.insert(param);
                // 添加动物id
                param.setAnimalId(id);
            } else {
                // 添加动物id
                param.setAnimalId(detailsResult.getAnimal().getId());
            }
            param.setFarmerId(loginUser.getUser().getId());
            param.setFarmerPhone(loginUser.getUser().getPhone());
            param.setFarmerName(extraInfo.getRealName());
            param.setFarmerAvatar(loginUser.getUser().getAvatarUrl());
            param.setFarmerIdNo(extraInfo.getIdCard());
            param.setFarmerAddress(extraInfo.getAddress());
            // 生成防疫编号
            param.setVaccinNo(String.valueOf(idWorker.nextId()));
            // 添加创建时间
            param.setCreateTime(new Date());
            // 添加检疫时间
            param.setVaccinTime(new Date());
            //防疫信息组装
            vaccins.add(param);
        }
        // 提交防疫数据+补偿积分
        if (ObjectUtils.isNotEmpty(vaccins)) {
            boolean ret=this.nbmhVaccinService.saveBatch(vaccins);
            if (ret) {
                this.integralHandle(vaccins);
                return Result.ok(resultAccinParams);
            }
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }


    @GetMapping("getByEarNo/{earNo}")
    @Operation(summary="根据耳标查询防疫信息")
    @Parameters({@Parameter(description="耳标", name="earNo")})
    public Result getByEarNo(@PathVariable("earNo") String earNo) {
        return Result.ok(this.nbmhVaccinService.getByEarNo(earNo));
    }


    @PostMapping("/record")
    @Operation(summary="防疫员端-防疫记录")
    @Inner(false)
    public Result record(@RequestBody PreventOfficerRecordParam param) {

        //返回结果集
        List<VaccinRecordDetailsResult> vaccinRecordDetailsResults=new ArrayList<>();

        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
        //防疫站id
        if (ObjectUtils.isNotEmpty(param.getPreventStationId())) {
            queryWrapper.eq(NbmhVaccin::getPreventStationId, param.getPreventStationId());
        }
        //防疫人员id
        if (ObjectUtils.isNotEmpty(param.getVaccinUserId())) {
            queryWrapper.eq(NbmhVaccin::getVaccinUserId, param.getVaccinUserId());
        }
        //时间 查询条件为年月日匹配
        if (ObjectUtils.isNotEmpty(param.getVaccinDate())) {
            String strStart=DateFormatUtils.format(param.getVaccinDate(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        //获取防疫记录数据集
        List<NbmhVaccin> vaccins=nbmhVaccinService.list(queryWrapper);

        //数据分组
        Map<String, List<NbmhVaccin>> groupMap=vaccins.stream()
                .collect(Collectors.groupingBy(u -> u.getFarmerId() + "|" + u.getAnimalType()));
        //分组处理数据
        for (Map.Entry<String, List<NbmhVaccin>> entry : groupMap.entrySet()) {
            VaccinRecordDetailsResult vaccinRecordDetailsResult=new VaccinRecordDetailsResult();
            vaccinRecordDetailsResult.setUserName(entry.getValue().get(0).getFarmerName());
            vaccinRecordDetailsResult.setAnimaltype(entry.getValue().get(0).getAnimalType());
            vaccinRecordDetailsResult.setVaccinNum(entry.getValue().size());
            vaccinRecordDetailsResult.setCreateTime(entry.getValue().get(0).getCreateTime());
            vaccinRecordDetailsResults.add(vaccinRecordDetailsResult);
        }
        return Result.ok(vaccinRecordDetailsResults);
    }


    @PostMapping("/personalPageRecord")
    @Operation(summary="站长-防疫员个人页-防疫记录")
    @Inner(false)
    public Result personalPageRecord(@RequestBody QueryRequest<NbmhVaccin> param) {
        //返回结果
        List<VaccinRecordResult> vaccinRecordResultList=new ArrayList<>();
        //获取防疫记录分页数据集
        IPage<NbmhVaccin> vaccinIPage=this.nbmhVaccinService.getByPage(param);
//        vaccinIPage.getRecords().forEach(item -> {
//            //返回结果数据组装
//            VaccinRecordResult vaccinRecordResult=new VaccinRecordResult();
//            //对象转换
//            BeanUtils.copyProperties(item, vaccinRecordResult);
//            //set动物信息
//            vaccinRecordResult.setAnimal(this.nbmhAnimalService.getById(item.getAnimalId()));
//            //返回结果赋值
//            vaccinRecordResultList.add(vaccinRecordResult);
//        });
        return Result.ok(vaccinIPage.getRecords());
    }


    @PostMapping("/details")
    @Operation(summary="防疫员端-防疫详情")
    @Inner(false)
    public Result details(@RequestBody NbmhVaccin vaccin) {
        return Result.ok(this.nbmhVaccinService.details(vaccin));
    }


    @PostMapping("/num")
    @Operation(summary="防疫员 -已防疫数量(统计)")
    @Inner(false)
    public Result getNum(@RequestBody NbmhVaccin param) {
        return Result.ok(this.nbmhVaccinService.getNum(param));
    }


    @PostMapping("/getVaccinPage")
    @Operation(summary="站长-防疫员个人页-防疫记录")
    @Inner(false)
    public Result getVaccinPage(@RequestBody QueryRequest<VaccinParam> param) {
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(param.getQueryEntity())) {
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getVaccinDate())) {
                //时间 查询条件为年月日匹配
                String strStart=DateFormatUtils.format(param.getQueryEntity().getVaccinDate(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            } else {
                //时间 查询条件为年月日匹配
                String strStart=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getFarmerName())) {
                queryWrapper.like(NbmhVaccin::getFarmerName, param.getQueryEntity().getFarmerName());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getFarmerAddress())) {
                queryWrapper.like(NbmhVaccin::getFarmerAddress, param.getQueryEntity().getFarmerAddress());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getPreventStationId())) {
                queryWrapper.eq(NbmhVaccin::getPreventStationId, param.getQueryEntity().getPreventStationId());
            }
        } else {
            //时间 查询条件为年月日匹配
            String strStart=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        queryWrapper.orderByDesc(NbmhVaccin::getCreateTime);
        //返回结果
        List<NbmhVaccin> vaccins=nbmhVaccinService.list(queryWrapper);
        /**
         * 处理结果分组
         */
        Map<Long, List<NbmhVaccin>> groupMap=vaccins.stream()
                .collect(Collectors.groupingBy(va -> va.getFarmerId()));
        //返回结果
        List<VaccinResult> results=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<Long, List<NbmhVaccin>> entry : groupMap.entrySet()) {
            VaccinResult vaccinResult=new VaccinResult();
            vaccinResult.setFarmerIdNo(entry.getValue().get(0).getFarmerIdNo());
            vaccinResult.setUserId(entry.getValue().get(0).getFarmerId());
            vaccinResult.setUserName(entry.getValue().get(0).getFarmerName());
            vaccinResult.setUserAddress(entry.getValue().get(0).getFarmerAddress());
            vaccinResult.setAnimalType(entry.getValue().get(0).getAnimalType());
            vaccinResult.setUserAvatarUrl(entry.getValue().get(0).getFarmerAvatar());
            vaccinResult.setUserPhone(entry.getValue().get(0).getFarmerPhone());
            vaccinResult.setImmunizedNum(entry.getValue().size());
            vaccinResult.setVaccinDate(entry.getValue().get(0).getCreateTime());
            vaccinResult.setNotImmune(0);
            vaccinResult.setTotalNum(entry.getValue().size());
            results.add(vaccinResult);
        }

        List<VaccinResult> vaccinResultList=results.stream().skip((param.getPageNum() - 1) * param.getPageSize()).limit(param.getPageSize()).
                collect(Collectors.toList());
        VaccinPageResult vaccinPageResult=new VaccinPageResult();
        vaccinPageResult.setList(vaccinResultList);
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapperCount=new LambdaQueryWrapper<>();
        queryWrapperCount.eq(NbmhVaccin::getPreventStationId, param.getQueryEntity().getPreventStationId());

        //时间 查询条件为年月日匹配
        String strStart=DateFormatUtils.format(param.getQueryEntity().getVaccinDate(), "yyyy-MM-dd");
        queryWrapperCount.apply("date_format (create_time,'%Y') = date_format('" + strStart + "','%Y')");
        vaccinPageResult.setYearTotalNum(nbmhVaccinService.count(queryWrapperCount));
        return Result.ok(vaccinPageResult);
    }


    @PostMapping("/getVaccinDetails")
    @Operation(summary="站长-防疫员个人页-防疫记录")
    @Inner(false)
    public Result getVaccinDetails(@RequestBody VaccinParam param) {
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(param.getUserId())) {
            //时间 查询条件为年月日匹配
            queryWrapper.eq(NbmhVaccin::getFarmerId, param.getUserId());
        }
        if (!ObjectUtils.isEmpty(param.getVaccinDate())) {
            //时间 查询条件为年月日匹配
            String strStart=DateFormatUtils.format(param.getVaccinDate(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        //返回结果
        List<NbmhVaccin> vaccins=nbmhVaccinService.list(queryWrapper);

        //返回结果
        VaccinResult results=new VaccinResult();
        //防疫动物列表
        List<AnimalResult> animalResultList=new ArrayList<>();
        //防疫人员
        List<String> preventPersonne=new ArrayList<>();
        //疫苗种类
        List<String> vaccines=new ArrayList<>();
        for (NbmhVaccin va : vaccins) {
            preventPersonne.add(va.getVaccinUser());
            preventPersonne=this.removeRepeat(preventPersonne);
            vaccines=this.strHandle(vaccines, va.getVaccinType());
            results.setUserPhone(va.getFarmerPhone());
            results.setUserAvatarUrl(va.getFarmerAvatar());
            results.setUserId(va.getFarmerId());
            results.setUserName(va.getFarmerName());
            results.setVaccinDate(va.getVaccinTime());
            //动物信息
            AnimalResult animalResult=new AnimalResult();
            animalResult.setType(va.getAnimalType());
            animalResult.setEarNo(va.getEarNo());
            animalResult.setAge(va.getAnimalAge());
            animalResult.setWeight(va.getAnimalWeight());
            animalResult.setAnimalUrl(va.getAnimalUrl());
            animalResultList.add(animalResult);
        }
        results.setPreventPersonnel(preventPersonne);
        results.setTotalNum(vaccins.size());
        results.setVaccines(vaccines);
        results.setAnimalResultList(animalResultList);
        return Result.ok(results);
    }


    @PostMapping("/preventionOfficerStatistics")
    @Operation(summary="站长-防疫统计")
    @Inner(false)
    public Result preventionOfficerStatistics(@RequestBody PreventOfficerRecordParam param) {
        VaccinPODtatistissResult result=new VaccinPODtatistissResult();
        //防疫员积分
        CurUserInfo userInfo=userService.queryCurUserInfo(param.getVaccinUserId(), 4).getData();
        if (ObjectUtils.isEmpty(userInfo.getUser())) {
            return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
        }
        //积分
        result.setIntegralNum(userInfo.getUser().getIntegral());
        //农牧币
        result.setCoinNum(userInfo.getUser().getCoin());
        //防疫数量
        result.setVaccinNum(nbmhVaccinService.count(Wrappers.<NbmhVaccin>query().lambda().eq(NbmhVaccin::getVaccinUserId, param.getVaccinUserId())));
        //检疫数量
        result.setInspectNum(inspectService.count(Wrappers.<NbmhInspect>query().lambda().eq(NbmhInspect::getVaccinId, param.getVaccinUserId())));
        //举报数量
        result.setCheckNum(checkService.count(Wrappers.<NbmhCheck>query().lambda().eq(NbmhCheck::getCheckId, param.getVaccinUserId())));
        return Result.ok(result);
    }


    /**
     * list 去重 String
     *
     * @param arr
     */
    public List<String> removeRepeat(List<String> arr) {
        Set set=new HashSet();
        List newList=new ArrayList();
        for (String str : arr) {
            if (set.add(str)) {
                newList.add(str);
            }
        }
        return newList;
    }

    /**
     * str 处理字符串力字符串
     *
     * @param arr
     * @param str
     */
    public List<String> strHandle(List<String> arr, String str) {
        String[] s1=str.split(",");
        for (String each : s1) {
            arr.add(each);
        }
        return this.removeRepeat(arr);
    }





    /**
     * 积分换算
     *
     * @param params
     */
    public void integralHandle(List<NbmhVaccin> params) {
        List<NbmhBaseConfig> baseConfigs=(List<NbmhBaseConfig>) baseConfigService.getBySubject("prevent").getData();
        int isOpen=0;//是否开启
        BigDecimal staffRatio=new BigDecimal("0.00"); //防疫员分成比例
        BigDecimal rewardAmount=new BigDecimal("0.00");//每次总积分数
        for (NbmhBaseConfig baseConfig : baseConfigs) {
            if ("staff_ratio".equals(baseConfig.getConfigKey())) {
                staffRatio=new BigDecimal(baseConfig.getConfigValue()).divide(new BigDecimal(10));//半分比计算除以10得到分成比例
            } else if ("reward_amount".equals(baseConfig.getConfigKey())) {
                rewardAmount=new BigDecimal(baseConfig.getConfigValue());
            } else if ("is_open".equals(baseConfig.getConfigKey())) {
                isOpen=Integer.parseInt(baseConfig.getConfigValue());
            } else {
                continue;
            }
        }
        if (isOpen == 1) {
            //积分记录处理结果集
            List<NbmhUserIntegralRecord> userIntegralRecords=new ArrayList<>();
            //遍历处理数据
            for (NbmhVaccin v : params) {
                //站长信息获取  1普通用户 2专家 3站长 4防疫员 5养殖户 6商家
                CurUserInfo userInfoResult=userService.queryCurUserInfo(v.getVaccinUserId(), 4).getData();
                if (ObjectUtils.isNotEmpty(userInfoResult) && ObjectUtils.isNotEmpty(userInfoResult.getUser())) {
                    NbmhUserExtraInfo userExtraInfo=userInfoResult.getExtraInfo();
                    NbmhUser user=userInfoResult.getUser();

                    NbmhUserIntegralRecord NbmhUserIntegralRecord=new NbmhUserIntegralRecord();
                    NbmhUserIntegralRecord.setUserId(v.getFarmerId());
                    NbmhUserIntegralRecord.setUserName(v.getFarmerName());
                    NbmhUserIntegralRecord.setUserAvatarUrl(v.getFarmerAvatar());
                    NbmhUserIntegralRecord.setVaccinId(v.getVaccinUserId());
                    NbmhUserIntegralRecord.setVaccinUser(v.getVaccinUser());
                    NbmhUserIntegralRecord.setVaccinAvatarUrl(user.getAvatarUrl());
                    NbmhUserIntegralRecord.setStatus(0);
                    NbmhUserIntegralRecord.setCreateTime(new Date());
                    NbmhUserIntegralRecord.setIsIncome(1);

                    //当前防疫人为站长
                    if (ObjectUtils.isEmpty(userExtraInfo)) {
                        user.setIntegral(user.getIntegral() + rewardAmount.intValue());
                        //T站长积分更新
                        userService.integralUpdate(user);
                        NbmhUserIntegralRecord.setSource(2);
                        NbmhUserIntegralRecord.setIntegral(rewardAmount.intValue());
                        userIntegralRecords.add(NbmhUserIntegralRecord);
                    } else {//当前防疫人为防疫员
                        //防疫员积分换算 四舍五入
                        int newStaffRatio=rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                        NbmhUserIntegralRecord.setSource(2);
                        NbmhUserIntegralRecord.setIntegral(newStaffRatio);
                        userIntegralRecords.add(NbmhUserIntegralRecord);
                        //防疫员积分更新
                        user.setIntegral(user.getIntegral() + newStaffRatio);
                        userService.integralUpdate(user);
                        //站长积分换算
                        int stationMasterIntegral=rewardAmount.subtract(rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();


                        NbmhUserIntegralRecord stationMasterIntegralRecord=new NbmhUserIntegralRecord();
                        stationMasterIntegralRecord.setUserId(v.getFarmerId());
                        stationMasterIntegralRecord.setUserName(v.getFarmerName());
                        stationMasterIntegralRecord.setUserAvatarUrl(v.getFarmerAvatar());
                        stationMasterIntegralRecord.setVaccinId(v.getVaccinUserId());
                        stationMasterIntegralRecord.setVaccinUser(v.getVaccinUser());
                        stationMasterIntegralRecord.setVaccinAvatarUrl(user.getAvatarUrl());
                        stationMasterIntegralRecord.setStatus(0);
                        stationMasterIntegralRecord.setCreateTime(new Date());
                        stationMasterIntegralRecord.setIsIncome(1);
                        stationMasterIntegralRecord.setSource(4);
                        stationMasterIntegralRecord.setIntegral(stationMasterIntegral);
                        userIntegralRecords.add(stationMasterIntegralRecord);

                        CurUserInfo stationmasterResult=userService.queryCurUserInfo(userExtraInfo.getParentId(), 3).getData();
                        NbmhUser stationmaster=stationmasterResult.getUser();
                        stationmaster.setIntegral(stationmaster.getIntegral() + stationMasterIntegral);
                        userService.integralUpdate(user);
                    }
                }
            }

            //添加积分记录
            if (ObjectUtils.isNotEmpty(userIntegralRecords)) {
                nbmhUserIntegralRecordService.saveBatch(userIntegralRecords);
            }
        }
    }
}
