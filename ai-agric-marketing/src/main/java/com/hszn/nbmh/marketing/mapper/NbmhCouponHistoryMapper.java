package com.hszn.nbmh.marketing.mapper;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 优惠券领取历史记录 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
public interface NbmhCouponHistoryMapper extends BaseMapper<NbmhCouponHistory> {

    /**
     * 查询可用的通用优惠券及指定店铺ID优惠券
     *
     * @param shopIds
     * @param userId
     * @return list
     */
    List<NbmhCouponHistory> findUsableByShopId(@Param("shopIds") Set<Long> shopIds, @Param("userId") Long userId);
}
