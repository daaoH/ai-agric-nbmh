package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

        //添加条件
        LambdaQueryWrapper<NbmhInspect> queryWrapper=new LambdaQueryWrapper<>();
        //根据动物ids获取检疫数据
        queryWrapper.in(NbmhInspect::getAnimalId, params.getIds());
        queryWrapper.eq(NbmhInspect::getStatus, 0);
        List<NbmhInspect> inspects=inspectService.list(queryWrapper);
        if (!ObjectUtils.isEmpty(inspects)) {
            Iterator<Long> it=params.getIds().iterator();
            while (it.hasNext()) {
                inspects.forEach(i -> {
                    Long x=it.next();
                    if (x.equals(i.getAnimalId())) {
                        it.remove();
                    }
                });
            }
            inspects.forEach(in -> {
                in.setStatus(1);
            });
            inspectService.saveBatch(inspects);
        }
        //动物耳标ids数量大于零 则创建新的检疫数据 状态直接更新为已检疫
        if (params.getIds().size() > 0) {
            //获取当前审批数据集合
            List<NbmhInspect> inspectList=new ArrayList<>();
            //TODO
            params.getIds().stream().forEach(id -> {
                NbmhInspect inspect=new NbmhInspect();
                inspect.setCreateTime(new Date());
                inspect.setDestination(params.getDestination());
                inspect.setInspectNumber(params.getInspectNumber());
                inspect.setStatus(2);
                inspect.setVaccinId(params.getVaccinId());
                inspect.setSubmitBy(params.getSubmitBy());
                inspect.setSubmitByPhone(params.getSubmitByPhone());
                inspectList.add(inspect);
            });
            //动物检疫-批量新增
            boolean ret=this.inspectService.saveBatch(inspectList);
            if (ret) {
                return Result.ok();
            }
        }
        return Result.failed(CommonEnum.DATA_SUBMIT_FAILED.getMsg());
    }


    public static void main(String[] args) {
        List<Long> ids1=new ArrayList<>();
        ids1.add(1L);
        ids1.add(2L);
        ids1.add(3L);
        ids1.add(4L);
        List<Long> ids2=new ArrayList<>();
        ids2.add(1L);
        ids2.add(2L);
        ids2.add(3L);
        ids2.add(4L);

        Iterator<Long> it=ids1.iterator();
        while (it.hasNext()) {
            ids2.forEach(i -> {
                Long x=it.next();
                if (x.equals(i)) {
                    it.remove();
                }
            });
        }
        System.out.println(ids1.toString());
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
