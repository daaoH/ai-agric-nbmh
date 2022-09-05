package com.hszn.nbmh.marketing.service.impl;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.mapper.NbmhCouponHistoryMapper;
import com.hszn.nbmh.marketing.service.INbmhCouponHistoryService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 优惠券领取历史记录 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Slf4j
@Service("couponHistoryService")
@Transactional(rollbackFor = Exception.class)
public class NbmhCouponHistoryServiceImpl extends ServiceImpl<NbmhCouponHistoryMapper, NbmhCouponHistory> implements INbmhCouponHistoryService {

    @Override
    public List<NbmhCouponHistory> findUsableByShopId(Set<Long> shopIds, Long userId) {
        return baseMapper.findUsableByShopId(shopIds, userId);
    }
}
