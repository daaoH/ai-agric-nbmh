package com.hszn.nbmh.user.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhUserVip;
import com.hszn.nbmh.user.api.entity.NbmhVipPrice;
import com.hszn.nbmh.user.api.entity.NbmhVipRightsAndInterests;
import com.hszn.nbmh.user.service.INbmhUserVipService;
import com.hszn.nbmh.user.service.INbmhVipPriceService;
import com.hszn.nbmh.user.service.INbmhVipRightsAndInterestsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户vip价格绑定表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-user-vip")
public class NbmhUserVipController {
    private final INbmhUserVipService userVipService;

    private final INbmhVipPriceService vipPriceService;

    private final INbmhVipRightsAndInterestsService vipRightsAndInterestsService;

    @GetMapping("/getByUid/{uid}")
    @Operation(summary="VIP权益及旗下权益明细获取")
    @Inner(false)

    public Result getByUid(@PathVariable("uid") @NonNull Long uid) {

        //返回结果集
        List<NbmhVipRightsAndInterests> resultList=new ArrayList<>();
        //获取用户会员权益全部有效
        List<NbmhUserVip> userVips=userVipService.list(Wrappers.<NbmhUserVip>query().lambda().eq(NbmhUserVip::getUserId, uid).eq(NbmhUserVip::getStatus, 0));
        //用户有效权益明细id
        List<Long> vipPriceIds=new ArrayList<>();
        if (ObjectUtils.isNotEmpty(userVips)) {
            userVips.forEach(uv -> {
                vipPriceIds.add(uv.getVipPriceId());
            });
            List<NbmhVipPrice> vipPrices=vipPriceService.list(Wrappers.<NbmhVipPrice>query().lambda().in(NbmhVipPrice::getId, vipPriceIds));
            //数据分组
            Map<Long, List<NbmhVipPrice>> groupMap=vipPrices.stream()
                    .collect(Collectors.groupingBy(v -> v.getVipRightsAndInterestsId()));
            //分组处理数据
            for (Map.Entry<Long, List<NbmhVipPrice>> entry : groupMap.entrySet()) {
                NbmhVipRightsAndInterests vipRightsAndInterests=vipRightsAndInterestsService.getById(entry.getKey());
                vipRightsAndInterests.getVipPrices().add((NbmhVipPrice) entry.getValue());
                //权益明细数据排序(正序)
                vipRightsAndInterests.getVipPrices().stream().sorted(Comparator.comparing(NbmhVipPrice::getSort));
                resultList.add(vipRightsAndInterests);
            }
        }
        return Result.ok(resultList);
    }

}
