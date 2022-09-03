package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.AnimalResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinPageResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinRecordResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinResult;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.prevent.service.INbmhUserIntegralRecordService;
import com.hszn.nbmh.prevent.service.INbmhVaccinService;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.hszn.nbmh.third.feign.RemoteBaseConfigService;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
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
    //动物接口
    private final INbmhAnimalService nbmhAnimalService;

    //用户积分记录
    private final INbmhUserIntegralRecordService nbmhUserIntegralRecordService;

    //用户接口
    private final RemoteUserService userService;


    //配置接口
    private final RemoteBaseConfigService baseConfigService;

    @PostMapping("/submit")
    @Operation(summary="防疫员端-免疫登记")
    @Inner(false)
    public Result submit(@RequestBody List<VaccinParam> params) {
        List<NbmhVaccin> vaccins=new ArrayList<>();
        for (VaccinParam param : params) {
            // 移动端防疫信息登记同步创建动物信息
            Long id=this.nbmhAnimalService.insert(param);
            // 初始化
            SnowFlakeIdUtil idWorker=new SnowFlakeIdUtil(1, 0);
            // 生成防疫编号
            param.setVaccinNo(String.valueOf(idWorker.nextId()));
            // 添加动物id
            param.setAnimalId(id);
            // 添加创建时间
            param.setCreateTime(new Date());
            // 添加检疫时间
            param.setVaccinTime(new Date());
            //防疫信息组装
            vaccins.add(param);
        }
        // 提交防疫数据
        if (ObjectUtils.isNotEmpty(vaccins)) {
            boolean ret=this.nbmhVaccinService.saveBatch(vaccins);
            if (ret) {
                List<NbmhBaseConfig> baseConfigs=(List<NbmhBaseConfig>) baseConfigService.getBySubject("prevent").getData();
                int isOpen=0;
//                BigDecimal leaderRatio=new BigDecimal("0.00");
                BigDecimal staffRatio=new BigDecimal("0.00");
                BigDecimal rewardAmount=new BigDecimal("0.00");
                for (NbmhBaseConfig baseConfig : baseConfigs) {
                    if ("staff_ratio".equals(baseConfig.getConfigKey())) {
                        staffRatio=new BigDecimal(baseConfig.getConfigValue());
                    } else if ("reward_amount".equals(baseConfig.getConfigKey())) {
                        rewardAmount=new BigDecimal(baseConfig.getConfigValue()).multiply(new BigDecimal(params.size()));
                    } else if ("is_open".equals(baseConfig.getConfigKey())) {
                        isOpen=Integer.parseInt(baseConfig.getConfigValue());
                    } else {
//                        leaderRatio=new BigDecimal(baseConfig.getConfigValue());
                    }
                }
                if (isOpen == 1) {
                    //站长信息获取  1普通用户 2专家 3站长 4防疫员 5养殖户 6商家
                    Result<CurUserInfo> userInfoResult=userService.queryCurUserInfo(params.get(0).getVaccinUserId(), 1);
                    if (!ObjectUtils.isEmpty(userInfoResult)) {
                        NbmhUserExtraInfo userExtraInfo=userInfoResult.getData().getExtraInfo();
                        NbmhUser user=userInfoResult.getData().getUser();
                        //当前防疫人为站长
                        if (userExtraInfo.getParentId().equals(0L)) {
                            user.setIntegral(user.getIntegral() + rewardAmount.intValue());
                            //T站长积分更新
                            userService.integralUpdate(user);
                            this.userIntegralRecordAdd(NbmhUserIntegralRecord.builder().userId(params.get(0).getFarmId())
                                    .userName(!ObjectUtils.isEmpty(params.get(0).getFarmerName()) ? params.get(0).getFarmerName() : "")
                                    .userAvatarUrl(!ObjectUtils.isEmpty(params.get(0).getFarmerAvatar()) ? params.get(0).getFarmerAvatar() : "")
                                    .vaccinId(user.getId()).vaccinUser(user.getUserName())
                                    .vaccinAvatarUrl(!ObjectUtils.isEmpty(user.getAvatarUrl()) ? user.getAvatarUrl() : "")
                                    .source(3).integral(rewardAmount.intValue()).status(0).createTime(new Date()).isIncome(1).build());
                        } else {//当前防疫人为防疫员
                            //防疫员积分换算 四舍五入
                            int newStaffRatio=rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                            this.userIntegralRecordAdd(NbmhUserIntegralRecord.builder().userId(params.get(0).getFarmId())
                                    .userName(!ObjectUtils.isEmpty(params.get(0).getFarmerName()) ? params.get(0).getFarmerName() : "")
                                    .userAvatarUrl(!ObjectUtils.isEmpty(params.get(0).getFarmerAvatar()) ? params.get(0).getFarmerAvatar() : "")
                                    .vaccinId(user.getId()).vaccinUser(user.getUserName())
                                    .vaccinAvatarUrl(!ObjectUtils.isEmpty(user.getAvatarUrl()) ? user.getAvatarUrl() : "")
                                    .source(3).integral(newStaffRatio).status(0).createTime(new Date()).isIncome(1).build());
                            //防疫员积分更新
                            user.setIntegral(user.getIntegral() + newStaffRatio);
                            userService.integralUpdate(user);
                            //站长积分换算
                            int stationMasterIntegral=rewardAmount.subtract(rewardAmount.multiply(staffRatio).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
                            this.userIntegralRecordAdd(NbmhUserIntegralRecord.builder().userId(params.get(0).getFarmId())
                                    .userName(!ObjectUtils.isEmpty(params.get(0).getFarmerName()) ? params.get(0).getFarmerName() : "")
                                    .userAvatarUrl(!ObjectUtils.isEmpty(params.get(0).getFarmerAvatar()) ? params.get(0).getFarmerAvatar() : "")
                                    .vaccinId(user.getId()).vaccinUser(user.getUserName())
                                    .vaccinAvatarUrl(!ObjectUtils.isEmpty(user.getAvatarUrl()) ? user.getAvatarUrl() : "")
                                    .source(4).integral(stationMasterIntegral).status(0).createTime(new Date()).isIncome(1).build());
                            Result<CurUserInfo> stationmasterResult=userService.queryCurUserInfo(userExtraInfo.getParentId(), 1);
                            NbmhUser stationmaster=stationmasterResult.getData().getUser();
                            stationmaster.setIntegral(stationmaster.getIntegral() + stationMasterIntegral);
                            userService.integralUpdate(user);
                        }
                    }
                }
                return Result.ok();
            }
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        }
        return Result.failed("录入数据有误!请确认数据信息的正确性!");
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
    public Result record(@RequestBody QueryRequest<NbmhVaccin> param) {
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


    /**
     * list 去重
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
     * 添加积分记录
     *
     * @param param
     */
    public void userIntegralRecordAdd(NbmhUserIntegralRecord param) {
        nbmhUserIntegralRecordService.save(param);
    }


}
