package com.hszn.nbmh.shop.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.shop.api.entity.NbmhShopExtraInfo;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.shop.api.params.input.ShopEditParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoSearchParam;
import com.hszn.nbmh.shop.service.INbmhShopExtraInfoService;
import com.hszn.nbmh.shop.service.INbmhShopInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 店铺信息表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2022-08-31
 */
@Tag(name = "shop-info", description = "店铺信息接口")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/shop-info")
public class NbmhShopInfoController {
    private final INbmhShopInfoService shopInfoService;

    @Resource
    private INbmhShopExtraInfoService shopExtraInfoService;

    public NbmhShopInfoController(INbmhShopInfoService shopInfoService) {
        this.shopInfoService = shopInfoService;
    }

    /**
     * 开店接口,保存开店信息
     *
     * @return
     */
    @Inner(false)
    @Operation(summary = "开店接口")
    @PostMapping("/openShop")
    public Result<String> openShop(@RequestBody @Validated ShopInfoParam shopInfo) {
        boolean result = shopInfoService.saveShop(shopInfo);
        if (result) {
            return Result.ok("开店成功");
        }
        return Result.failed("开店失败,请重试");
    }

    @Inner(false)
    @Operation(summary = "根据Id获取店铺详情")
    @GetMapping("/detail")
    public Result<NbmhShopInfo> detail(@RequestParam("id") Long id) {
        NbmhShopInfo result = shopInfoService.getById(id);
        return Result.ok(result);
    }

    @Inner(false)
    @Operation(summary = "根据条件查询")
    @PostMapping("/search")
    public Result<Page<NbmhShopInfo>> search(@RequestBody QueryRequest<ShopInfoSearchParam> param) {
        Page<NbmhShopInfo> result = shopInfoService.search(param);
        return Result.ok(result);
    }

    @Inner(false)
    @Operation(summary = "店铺修改")
    @PostMapping("/update")
    public Result<String> update(@RequestBody ShopEditParam param) {
        boolean result = shopInfoService.modify(param);
        if (result) {
            return Result.ok("修改成功");
        }
        return Result.failed("修改失败,请重试");
    }

    @Inner(false)
    @Operation(summary = "提交实体信息")
    @PostMapping("/commitEntityInfo")
    public Result<String> commitEntityInfo(@RequestBody NbmhShopExtraInfo info) {
        boolean result = shopExtraInfoService.save(info);
        if (result) {
            return Result.ok("提交成功");
        }
        return Result.failed("提交失败,请重试");
    }

    @Inner(false)
    @Operation(summary = "获取当前登录用户店铺详情")
    @GetMapping("/currentShopInfo")
    public Result<NbmhShopInfo> currentShopInfo() {
        NbmhShopInfo result = shopInfoService.currentShopInfo();
        return Result.ok(result);
    }

}
