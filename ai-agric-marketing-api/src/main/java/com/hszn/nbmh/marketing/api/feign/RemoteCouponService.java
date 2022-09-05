package com.hszn.nbmh.marketing.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.marketing.api.constant.MarketingPathConstant;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.api.fallback.RemoteCouponServiceFallback;
import com.hszn.nbmh.marketing.api.params.input.CouponParam;
import com.hszn.nbmh.marketing.api.params.input.GoodsParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 优惠券对外服务
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-03 16:00
 */
@FeignClient(value = ServiceNameConstant.MARKETING_SERVICE, path = MarketingPathConstant.COUPON_URL, fallback = RemoteCouponServiceFallback.class)
public interface RemoteCouponService {

    @PostMapping("provide")
    Result<String> couponProvide(@RequestBody CouponParam param);

    @GetMapping("accept")
    Result<String> couponAccept(Long couponId);

    @GetMapping("findCoupon")
    Result<List<NbmhCouponHistory>> findCoupon(@RequestParam("status") Integer status);

    @PostMapping("findUsableCoupon")
    Result<List<NbmhCouponHistory>> findUsableCoupon(@RequestBody List<GoodsParam> params);

}
