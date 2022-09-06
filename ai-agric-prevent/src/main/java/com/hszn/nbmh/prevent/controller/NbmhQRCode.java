package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.params.input.TradeReportPageParam;
import com.hszn.nbmh.prevent.api.params.out.TradeReportPageResult;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.third.feign.RemoteCodeImageService;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 防疫信息表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-6
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-qr_code")
@Tag(name="防疫端二维码调用接口")
public class NbmhQRCode {

    private final RemoteCodeImageService codeImageService;

    private final RemoteUserService userService;

    private final INbmhAnimalService animalService;

    @PostMapping("/tradeReportByPage")
    @Operation(summary="活体报备获取农户")
    public Result tradeReport(@RequestBody QueryRequest<TradeReportPageParam> param) {
        //返回结果集
        TradeReportPageResult tradeReportPageResult=new TradeReportPageResult<>();

        if (ObjectUtils.isEmpty(param.getQueryEntity())) {
            return Result.failed("请确认参数信息!");
        }
        //商贩信息
        CurUserInfo buyerUserInfo=userService.queryCurUserInfo(param.getQueryEntity().getBuyerId(), 6).getData();
        if (ObjectUtils.isEmpty(buyerUserInfo) ||
                ObjectUtils.isEmpty(buyerUserInfo.getUser()) ||
                ObjectUtils.isEmpty(buyerUserInfo.getExtraInfo())) {
            return Result.failed("请确认当前是用户身份是否为商贩用户!");
        }
        //养殖户信息
        CurUserInfo farmerUserInfo=userService.queryCurUserInfo(param.getQueryEntity().getFarmerId(), 5).getData();
        if (ObjectUtils.isEmpty(farmerUserInfo) ||
                ObjectUtils.isEmpty(farmerUserInfo.getUser()) ||
                ObjectUtils.isEmpty(farmerUserInfo.getExtraInfo())) {
            return Result.failed("请确认当前是用户身份是否为养殖用户!");
        }

        //添加条件
        LambdaQueryWrapper<NbmhAnimal> queryWrapper=new LambdaQueryWrapper<>();
        //农户id
        queryWrapper.eq(NbmhAnimal::getUserId, farmerUserInfo.getExtraInfo().getUserId());
        //状态   0=正常动物状态
        queryWrapper.eq(NbmhAnimal::getStatus, 0);

        //返回数据集
        List<NbmhAnimal> animals=animalService.list(queryWrapper);
        //获取数据集分组
        Map<Integer, List<NbmhAnimal>> groupMap=animals.stream().collect(Collectors.groupingBy(animal -> animal.getType()));

        //类型集合
        List<Integer> types=new ArrayList<>();

        //动物集合
        List<NbmhAnimal> animalList=new ArrayList<>();

        //分组处理数据
        for (Map.Entry<Integer, List<NbmhAnimal>> entry : groupMap.entrySet()) {
            types.add(entry.getValue().get(0).getType());
            if (entry.getValue().get(0).getType() == param.getQueryEntity().getAnimalType()) {
                animalList=animals.stream().skip((param.getPageNum() - 1) * param.getPageSize()).limit(param.getPageSize()).collect(Collectors.toList());
            }
        }
        tradeReportPageResult.setBuyerId(param.getQueryEntity().getBuyerId());
        tradeReportPageResult.setBuyerName(buyerUserInfo.getExtraInfo().getRealName());
        tradeReportPageResult.setBuyerPhone(buyerUserInfo.getUser().getPhone());
        tradeReportPageResult.setFarmerAvatarUrl(farmerUserInfo.getUser().getAvatarUrl());
        tradeReportPageResult.setFarmerId(param.getQueryEntity().getFarmerId());
        tradeReportPageResult.setFarmerName(farmerUserInfo.getExtraInfo().getRealName());
        tradeReportPageResult.setFarmerPhone(farmerUserInfo.getUser().getPhone());
        tradeReportPageResult.setList(animalList);
        return Result.ok(tradeReportPageResult);
    }


}
