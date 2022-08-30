package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.PageModelUtils;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.AnimalResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinPageResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinRecordResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinResult;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.prevent.service.INbmhVaccinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result record(@RequestBody QueryRequest<VaccinParam> param) {
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
    public Result personalPageRecord(@RequestBody QueryRequest<VaccinParam> param) {
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
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getCreateTime())) {
                //时间 查询条件为年月日匹配
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
            } else {
                String strStart=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
            }
        } else {
            String strStart=DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
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
            vaccinResult.setUserId(entry.getValue().get(0).getFarmerId());
            vaccinResult.setUserName(entry.getValue().get(0).getFarmerName());
            vaccinResult.setAnimalType(entry.getValue().get(0).getAnimalType());
            vaccinResult.setUserAvatarUrl(entry.getValue().get(0).getFarmerAvatar());
            vaccinResult.setUserPhone(entry.getValue().get(0).getFarmerPhone());
            vaccinResult.setImmunizedNum(entry.getValue().size());
            vaccinResult.setNotImmune(0);
            vaccinResult.setTotalNum(entry.getValue().size());
            results.add(vaccinResult);
        }
        PageModelUtils pageModel=new PageModelUtils();
        pageModel.setPageNum(param.getPageNum());
        pageModel.setPageSize(param.getPageSize());
        pageModel.setTotalRecords(results.size());
        pageModel.setList(results);

        VaccinPageResult vaccinPageResult=new VaccinPageResult();
        vaccinPageResult.setPageModel(pageModel);

        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapperCount=new LambdaQueryWrapper<>();
        queryWrapperCount.eq(NbmhVaccin::getPreventStationId, param.getQueryEntity().getPreventStationId());
        //时间 查询条件为年月日匹配
        String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
        queryWrapperCount.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
        vaccinPageResult.setYearTotalNum(nbmhVaccinService.count(queryWrapperCount));
        return Result.ok(pageModel);
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
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
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
        results.setTotalNum(vaccins.size());
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


}
