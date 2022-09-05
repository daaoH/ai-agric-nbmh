package com.hszn.nbmh.marketing.service;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 优惠券领取历史记录 服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
public interface INbmhCouponHistoryService extends IService<NbmhCouponHistory> {

    /**
     * 根据商铺ID查询可用优惠券
     *
     * @param shopIds
     * @param userId
     * @return
     */
    List<NbmhCouponHistory> findUsableByShopId(Set<Long> shopIds, Long userId);
}
