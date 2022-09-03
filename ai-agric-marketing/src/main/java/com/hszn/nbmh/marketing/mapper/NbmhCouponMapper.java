package com.hszn.nbmh.marketing.mapper;

import com.hszn.nbmh.marketing.api.entity.NbmhCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hszn.nbmh.marketing.api.params.out.CouponAcceptOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
public interface NbmhCouponMapper extends BaseMapper<NbmhCoupon> {

    /**
     * 根据优惠券ID和当前时间查询优惠券
     *
     * @param couponId
     * @param userId
     * @return list
     */
    List<CouponAcceptOut> getAcceptHistory(@Param("id") Long couponId, @Param("userId") Long userId);
}
