package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.params.input.BreederCensusParam;
import com.hszn.nbmh.prevent.api.params.out.AnimalResult;
import com.hszn.nbmh.prevent.api.params.out.BreederCensusResult;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.prevent.service.INbmhVaccinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 动物信息表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-animal")
@Tag(name="动物端接口")
public class NbmhAnimalController {

    //动物
    private final INbmhAnimalService nbmhAnimalService;
    //防疫
    private final INbmhVaccinService nbmhVaccinService;

    @PostMapping("/getByPage")
    @Operation(summary="动物列表")
    public Result getByPage(@RequestBody QueryRequest<NbmhAnimal> param) {
        //获取防疫记录分页数据集
        return Result.ok(this.nbmhAnimalService.getByPage(param).getRecords());
    }


    @PostMapping("/breederCensus")
    @Operation(summary="防疫员端(农户详情-动物列表,防疫记录,信用抵押)+(动物信息统计)")
    public Result breederCensus(@RequestBody QueryRequest<BreederCensusParam> param) {
        //返回结果集合
        BreederCensusResult breederCensusResult=new BreederCensusResult();
        /**
         * 组装参数
         */
        QueryRequest newParam=new QueryRequest();
        newParam.setPageNum(param.getPageNum());
        newParam.setPageSize(param.getPageSize());
        if (param.getQueryEntity().getRequestType().equals(1)) {//动物列表
            NbmhAnimal animal=new NbmhAnimal();
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getUserId())) {
                animal.setUserId(param.getQueryEntity().getUserId());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getAnimalType())) {
                animal.setType(param.getQueryEntity().getAnimalType());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getDataTime())) {
                animal.setCreateTime(param.getQueryEntity().getDataTime());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getStatus())) {
                animal.setStatus(param.getQueryEntity().getStatus());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getEarNo())) {
                animal.setEarNo(param.getQueryEntity().getEarNo());
            }
            newParam.setQueryEntity(animal);
            //获取动物列表
            breederCensusResult.setList(nbmhAnimalService.getByPage(newParam).getRecords());
        } else if (param.getQueryEntity().getRequestType().equals(2)) {// 防疫记录
            NbmhVaccin vaccin=new NbmhVaccin();
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getUserId())) {
                vaccin.setFarmerId(param.getQueryEntity().getUserId());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getAnimalType())) {
                vaccin.setAnimalType(param.getQueryEntity().getAnimalType());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getDataTime())) {
                vaccin.setCreateTime(param.getQueryEntity().getDataTime());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getStatus())) {
                vaccin.setStatus(param.getQueryEntity().getStatus());
            }
            if (!ObjectUtils.isEmpty(param.getQueryEntity().getEarNo())) {
                vaccin.setEarNo(param.getQueryEntity().getEarNo());
            }
            newParam.setQueryEntity(vaccin);
            breederCensusResult.setList(nbmhVaccinService.getByPage(newParam).getRecords());
        } else {//信用抵押
            breederCensusResult.setList(new ArrayList());
        }
        breederCensusResult.setStatisticsResult(nbmhAnimalService.getCensusByUserId(param.getQueryEntity().getUserId(), param.getQueryEntity().getAnimalType()));
        return Result.ok(breederCensusResult);
    }


    @PostMapping("/vaccin/record")
    @Operation(summary="防疫员端-动物列表")
    public Result record(@RequestBody QueryRequest<NbmhAnimal> param) {

        //返回结果
        List<AnimalResult> animalResults=new ArrayList<>();
        //获取防疫记录分页数据集
        IPage<NbmhAnimal> animalIPage=this.nbmhAnimalService.getByPage(param);
//        animalIPage.getRecords().forEach(item -> {
//            //返回结果数据组装
//            AnimalResult animalResult=new AnimalResult();
//            //对象转换
//            BeanUtils.copyProperties(item, animalResult);
//            //添加条件获取防疫信息
//            LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
//            //抵押状态
//            queryWrapper.eq(NbmhVaccin::getAnimalId, animalResult.getId());
//            queryWrapper.orderByDesc(NbmhVaccin::getCreateTime);
//            List<NbmhVaccin> vaccinsli=this.nbmhVaccinService.list(queryWrapper);
//            if (ObjectUtils.isNotEmpty(vaccinsli)) {
//                //set防疫员信息(根据时间倒序给出第一人)
//                animalResult.setVaccinUser(vaccinsli.get(0).getVaccinUser());
//                animalResult.setPreventTime(vaccinsli.get(0).getCreateTime());
//            } else {
//                //set防疫员信息(未能获取防疫信息赋值空)
//                animalResult.setVaccinUser("");
//            }
//            //返回结果赋值
//            animalResults.add(animalResult);
//        });
        return Result.ok(animalIPage.getRecords());
    }


    @GetMapping("getCensusByUserId/{userId}/{type}")
    @Operation(summary="根据农户统计动物信息")
    @Parameters({@Parameter(description="用户id", name="userId"), @Parameter(description="动物类型(0猪 1牛)", name="type")})
    public Result getCensusByUserId(@PathVariable("userId") Long userId, @PathVariable("type") Integer type) {
        return Result.ok(nbmhAnimalService.getCensusByUserId(userId, type));
    }


    @GetMapping("getByEarNo/{earNo}")
    @Operation(summary="根据耳标查询动物信息(包含检疫-防疫等信息)")
    @Parameters({@Parameter(description="耳标", name="earNo")})
    public Result getByEarNo(@PathVariable("earNo") String earNo) {

        return Result.ok(nbmhAnimalService.getByEarNo(earNo));
    }


    @PutMapping("updateById")
    @Operation(summary="更新动物信息")
    public Result updateById(@RequestBody NbmhAnimal param) {
        //更新
        this.nbmhAnimalService.updateById(param);
        return Result.ok();
    }


}
