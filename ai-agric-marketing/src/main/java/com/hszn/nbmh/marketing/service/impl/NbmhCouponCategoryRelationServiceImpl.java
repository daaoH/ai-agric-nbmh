package com.hszn.nbmh.marketing.service.impl;

import com.hszn.nbmh.marketing.api.entity.NbmhCouponCategoryRelation;
import com.hszn.nbmh.marketing.mapper.NbmhCouponCategoryRelationMapper;
import com.hszn.nbmh.marketing.service.INbmhCouponCategoryRelationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 优惠券分类关联 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Slf4j
@Service("couponCategoryRelationService")
@Transactional(rollbackFor = Exception.class)
public class NbmhCouponCategoryRelationServiceImpl extends ServiceImpl<NbmhCouponCategoryRelationMapper, NbmhCouponCategoryRelation> implements INbmhCouponCategoryRelationService {
    @Resource
    private NbmhCouponCategoryRelationMapper nbmhCouponCategoryRelationMapper;


}
