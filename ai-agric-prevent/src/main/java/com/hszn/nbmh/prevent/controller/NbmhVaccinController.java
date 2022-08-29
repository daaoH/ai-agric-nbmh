package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.VaccinRecordResult;
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
import java.util.Date;
import java.util.List;

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

}
