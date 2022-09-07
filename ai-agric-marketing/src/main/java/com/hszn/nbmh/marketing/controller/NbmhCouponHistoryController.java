package com.hszn.nbmh.marketing.controller;


import com.hszn.nbmh.marketing.service.INbmhCouponHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券领取历史记录 前端控制器
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/coupon-history")
public class NbmhCouponHistoryController {

    private final INbmhCouponHistoryService couponHistoryService;

    public NbmhCouponHistoryController(INbmhCouponHistoryService couponHistoryService) {
        this.couponHistoryService = couponHistoryService;
    }


}
