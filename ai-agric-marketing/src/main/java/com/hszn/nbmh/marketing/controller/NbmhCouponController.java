package com.hszn.nbmh.marketing.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.api.params.input.GoodsParam;
import com.hszn.nbmh.marketing.service.INbmhCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.hszn.nbmh.marketing.api.params.input.CouponParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/coupon")
@Tag(name = "/coupon", description = "优惠券接口")
public class NbmhCouponController {
    private final INbmhCouponService couponService;

    public NbmhCouponController(INbmhCouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * 优惠券发放
     */
    @Operation(description = "优惠券发放")
    @Inner(value = false)
    @PostMapping("provide")
    public Result<String> couponProvide(@RequestBody CouponParam param) {
        boolean result = couponService.provide(param);
        if (result) {
            return Result.ok("优惠券发放成功");
        }
        return Result.ok("优惠券发放失败");
    }

    /**
     * 优惠券领取
     */
    @Operation(description = "优惠券领取")
    @Inner(value = false)
    @GetMapping("accept")
    public Result<String> couponAccept(Long couponId) {
        boolean result = couponService.accept(couponId, 1);
        if (result) {
            return Result.ok("优惠券领取成功");
        }
        return Result.ok("优惠券领取失败");
    }

    /**
     * 获取用户可用优惠券
     *
     * @return
     */
    @Operation(description = "根据状态获取用户优惠券")
    @Inner(value = false)
    @GetMapping("findCoupon")
    public Result<List<NbmhCouponHistory>> findCoupon(@RequestParam("status") Integer status) {
        List<NbmhCouponHistory> result = couponService.findCoupon(status);
        return Result.ok(result);
    }


    /**
     * 根据商品获取可用优惠券
     *
     * @return
     */
    @Operation(description = "根据商品获取可用优惠券")
    @Inner(value = false)
    @PostMapping("findUsableCoupon")
    public Result<List<NbmhCouponHistory>> findUsableCoupon(@RequestBody List<GoodsParam> params) {
        List<NbmhCouponHistory> result = couponService.findUsableCoupon(params);
        return Result.ok(result);
    }

}
