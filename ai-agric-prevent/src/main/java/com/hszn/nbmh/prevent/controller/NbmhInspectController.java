package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.params.input.InspectExamineParam;
import com.hszn.nbmh.prevent.api.params.input.InspectRecordParam;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.service.INbmhInspectService;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
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

    //动物
    private final NbmhAnimalMapper animalMapper;

    //用户信息
    private final RemoteUserService userService;


    SnowFlakeIdUtil snowFlakeId=new SnowFlakeIdUtil(1L, 1L);

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
            inspects.forEach(inspect -> {
                inspect.setStatus(1);//已检疫状态
                inspect.setPreventStationId(params.getPreventStationId());//防疫站id
                inspect.setVaccinId(params.getVaccinId());//检疫人id
                inspect.setDestination(params.getDestination());//到达地
                inspect.setInspectNumber(params.getInspectNumber());//检疫编号
                inspect.setInspectProveUrl(params.getInspectProveUrl());//检疫证明
                inspect.setSubmitBy(params.getSubmitBy());//送检人
                inspect.setSubmitByPhone(params.getSubmitByPhone());//送检人电话
                inspect.setUpdateTime(new Date());//更新时间
            });
            inspectService.updateBatchById(inspects);
        }
        //动物耳标ids数量大于零 则创建新的检疫数据 状态直接更新为已检疫
        if (params.getIds().size() > 0) {
            //获取当前审批数据集合
            List<NbmhInspect> inspectList=new ArrayList<>();
            params.getIds().stream().forEach(id -> {
                NbmhInspect inspect=new NbmhInspect();
                inspect.setCreateTime(new Date());//创建时间
                inspect.setInspectNumber(params.getInspectNumber());//检疫编号
                inspect.setStatus(1);//已检疫状态
                inspect.setVaccinId(params.getVaccinId());//检疫人id
                inspect.setPreventStationId(params.getPreventStationId());//防疫站id
                inspect.setInspectProveUrl(params.getInspectProveUrl());//检疫证明
                inspect.setSubmitBy(params.getSubmitBy());//送检人
                inspect.setSubmitByPhone(params.getSubmitByPhone());//送检人电话
                inspect.setDestination(params.getDestination());//到达地
                inspect.setBuyerName(params.getBuyerName());//买家
                inspect.setBuyerCard(params.getBuyerCard());//买家身份证
                inspect.setBuyerPhone(params.getBuyerPhone());//买家电话
                inspect.setPlaceConsigned(params.getPlaceConsigned());//启运地
                inspect.setReportNumber(String.valueOf(snowFlakeId.nextId()));//报备编号
                //动物信息 TODO 效率问题
                NbmhAnimal animal=animalMapper.selectById(id);
                inspect.setAnimalId(id);
                inspect.setEarNo(animal.getEarNo());
                inspect.setAnimalType(animal.getType());
                inspect.setAnimalStatus(animal.getStatus());
                //用户信息
                CurUserInfo userInfo=userService.queryCurUserInfo(animal.getUserId(), 5).getData();
                inspect.setUserId(animal.getUserId());
                inspect.setUserName(userInfo.getExtraInfo().getRealName());
                inspect.setUserAvatarUrl(userInfo.getUser().getAvatarUrl());
                inspect.setUserPhone(userInfo.getUser().getPhone());

                inspectList.add(inspect);
            });
            //动物检疫-批量新增
            this.inspectService.saveBatch(inspectList);
        }
        return Result.ok();
    }


    @PostMapping("/record")
    @Operation(summary="防疫员-检疫记录")
    public Result record(@RequestBody InspectRecordParam params) {
        return Result.ok(inspectService.record(params));
    }

    @PostMapping("/recordDetails")
    @Operation(summary="防疫员-防疫记录")
    public Result recordDetails(@RequestBody InspectRecordParam params) {
        return Result.ok(inspectService.recordDetails(params));
    }


}
