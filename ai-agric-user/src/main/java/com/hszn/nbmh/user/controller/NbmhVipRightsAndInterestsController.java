package com.hszn.nbmh.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhVipPrice;
import com.hszn.nbmh.user.api.entity.NbmhVipRightsAndInterests;
import com.hszn.nbmh.user.service.INbmhVipPriceService;
import com.hszn.nbmh.user.service.INbmhVipRightsAndInterestsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * vip用户权益 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@RestController
@RequestMapping("/nbmh-vip-rights-and-interests")
@RequiredArgsConstructor
public class NbmhVipRightsAndInterestsController {


    private final INbmhVipRightsAndInterestsService vipRightsAndInterestsService;

    private final INbmhVipPriceService vipPriceService;

    @PostMapping("/submit")
    @Operation(summary="创建vip权益")
    @Inner(false)
    public Result submit(@RequestBody NbmhVipRightsAndInterests param) {

        // 提交vip价格创建
        if (vipRightsAndInterestsService.save(param)) {

            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PostMapping("/update")
    @Operation(summary="更新vip权益")
    @Inner(false)
    public Result update(@RequestBody NbmhVipRightsAndInterests param) {
        //更新违规记录
        if (vipRightsAndInterestsService.updateById(param)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    @PostMapping("/del/{id}")
    @Parameters({@Parameter(description="数据id", name="id")})
    @Operation(summary="删除VIP权益")
    @Inner(false)
    public Result del(@PathVariable("id") Long id) {
        // 删除规则信息
        if (vipRightsAndInterestsService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }

    @PostMapping("/getAll")
    @Operation(summary="VIP权益集合")
    @Inner(false)
    public Result getByPage() {
        return Result.ok(vipRightsAndInterestsService.list());
    }

    @PostMapping("/getByParam")
    @Operation(summary="根据参数获取数据集")
    @Inner(false)
    public Result getByParam(@RequestBody NbmhVipRightsAndInterests param) {
        return Result.ok(vipRightsAndInterestsService.list(
                Wrappers.<NbmhVipRightsAndInterests>query().lambda()
                        .like(NbmhVipRightsAndInterests::getTitle, param.getTitle())
                        .eq(NbmhVipRightsAndInterests::getStatus, 0)
                        .eq(NbmhVipRightsAndInterests::getId, param.getId())));
    }


    @PostMapping("/getAllAndDetails")
    @Operation(summary="VIP权益及旗下权益明细获取")
    @Inner(false)
    public Result getAllAndDetails() {
        //获取数据 组装数据
        List<NbmhVipRightsAndInterests> vipRightsAndInterests=vipRightsAndInterestsService.list();
        List<NbmhVipPrice> vipPrices=vipPriceService.list();
        vipRightsAndInterests.forEach(vi -> {
            vipPrices.forEach(vp -> {
                if (vi.getId().equals(vp.getVipRightsAndInterestsId())) {
                    vi.getVipPrices().add(vp);
                }
            });
        });
        //权益明细数据排序(正序)
        vipRightsAndInterests.forEach(v -> {
            v.getVipPrices().stream().sorted(Comparator.comparing(NbmhVipPrice::getSort));
        });

        return Result.ok(vipRightsAndInterests);
    }


}
