package com.hszn.nbmh.marketing.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.api.feign.RemoteCouponService;
import com.hszn.nbmh.marketing.api.params.input.CouponParam;

import java.util.List;

/**
 * 优惠券对外服务fallback
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-03 16:05
 */
public class RemoteCouponServiceFallback implements RemoteCouponService {
    @Override
    public Result<String> couponProvide(CouponParam param) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result<String> couponAccept(Long couponId) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result<List<NbmhCouponHistory>> findCoupon(Integer status) {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }
}
