package com.hszn.nbmh.marketing.service;

import com.hszn.nbmh.marketing.api.entity.NbmhCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.api.params.input.CouponParam;
import com.hszn.nbmh.marketing.api.params.input.GoodsParam;
import com.hszn.nbmh.marketing.api.params.out.CouponAcceptOut;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
public interface INbmhCouponService extends IService<NbmhCoupon> {

    /**
     * 优惠券发放接口
     *
     * @param param
     * @return boolean
     */
    boolean provide(CouponParam param);

    /**
     * 优惠券领取接口
     *
     * @param couponId
     * @return
     */
    boolean accept(Long couponId, Integer getType);

    /**
     * 优惠券验证接口
     *
     * @param couponId
     * @return
     */
    List<CouponAcceptOut> getAcceptHistory(Long couponId);

    /**
     * 根据状态获取优惠券信息
     *
     * @return
     */
    List<NbmhCouponHistory> findCoupon(Integer status);

    /**
     * 根据商品获取可用优惠券
     *
     * @param params
     * @return
     */
    List<NbmhCouponHistory> findUsableCoupon(List<GoodsParam> params);
}
