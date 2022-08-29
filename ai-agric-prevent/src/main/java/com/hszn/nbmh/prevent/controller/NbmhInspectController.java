package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.params.input.InspectExamineParam;
import com.hszn.nbmh.prevent.api.params.input.InspectRecordParam;
import com.hszn.nbmh.prevent.service.INbmhInspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 检疫 检擦 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-inspect")
@Tag(name="动物检疫端接口")
public class NbmhInspectController {

    //检疫
    private final INbmhInspectService inspectService;

    @GetMapping("getByEarNo/{earNo}")
    @Operation(summary="根据耳标获取检疫数据对象")
    @Parameters({@Parameter(description="耳标", name="earNo")})
    public Result getCensusByUserId(@PathVariable("earNo") String earNo) {
        return Result.ok(inspectService.getByEarNo(earNo));
    }


    @PostMapping("/examine")
    @Operation(summary="动物检疫-批量审批")
    public Result examine(@RequestBody InspectExamineParam params) {
        //获取当前审批数据集合
        List<NbmhInspect> result=new ArrayList<>();
        inspectService.listByIds(params.getIds()).stream().forEach(inspect -> {
            inspect.setCreateTime(new Date());
            inspect.setDestination(params.getDestination());
            inspect.setInspectNumber(params.getInspectNumber());
            inspect.setStatus(2);
            inspect.setVaccinId(params.getVaccinId());
            inspect.setInspectNumber(params.getInspectNumber());
            inspect.setSubmitBy(params.getSubmitBy());
            inspect.setSubmitByPhone(params.getSubmitByPhone());
            result.add(inspect);
        });
        //动物检疫-批量
        if (ObjectUtils.isNotEmpty(result)) {
            boolean ret=this.inspectService.saveBatch(result);
            if (ret) {
                return Result.ok();
            }
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        }
        return Result.failed("录入数据有误!请确认数据信息的正确性!");
    }


    @PostMapping("/record")
    @Operation(summary="防疫员-防疫记录")
    public Result record(@RequestBody InspectRecordParam params) {
        return Result.ok(inspectService.record(params));
    }

    @PostMapping("/recordDetails")
    @Operation(summary="防疫员-防疫记录")
    public Result recordDetails(@RequestBody InspectRecordParam params) {
        return Result.ok(inspectService.recordDetails(params));
    }


}
