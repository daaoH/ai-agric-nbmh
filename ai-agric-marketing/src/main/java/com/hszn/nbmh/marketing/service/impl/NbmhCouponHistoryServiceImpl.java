package com.hszn.nbmh.marketing.service.impl;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.mapper.NbmhCouponHistoryMapper;
import com.hszn.nbmh.marketing.service.INbmhCouponHistoryService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Resource
    private NbmhCouponHistoryMapper nbmhCouponHistoryMapper;


}
