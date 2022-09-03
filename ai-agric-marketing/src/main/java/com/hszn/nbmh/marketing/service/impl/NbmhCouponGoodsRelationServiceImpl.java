package com.hszn.nbmh.marketing.service.impl;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponGoodsRelation;
import com.hszn.nbmh.marketing.mapper.NbmhCouponGoodsRelationMapper;
import com.hszn.nbmh.marketing.service.INbmhCouponGoodsRelationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 优惠券与产品关联 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Slf4j
@Service("couponGoodsRelationService")
@Transactional(rollbackFor = Exception.class)
public class NbmhCouponGoodsRelationServiceImpl extends ServiceImpl<NbmhCouponGoodsRelationMapper, NbmhCouponGoodsRelation> implements INbmhCouponGoodsRelationService {
    @Resource
    private NbmhCouponGoodsRelationMapper nbmhCouponGoodsRelationMapper;


}
