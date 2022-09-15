package com.hszn.nbmh.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.admin.api.params.vo.SysAuthUser;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.marketing.api.entity.NbmhCoupon;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponCategoryRelation;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponGoodsRelation;
import com.hszn.nbmh.marketing.api.entity.NbmhCouponHistory;
import com.hszn.nbmh.marketing.api.params.input.CouponParam;
import com.hszn.nbmh.marketing.api.params.input.GoodsParam;
import com.hszn.nbmh.marketing.api.params.out.CouponAcceptOut;
import com.hszn.nbmh.marketing.mapper.NbmhCouponMapper;
import com.hszn.nbmh.marketing.service.INbmhCouponCategoryRelationService;
import com.hszn.nbmh.marketing.service.INbmhCouponGoodsRelationService;
import com.hszn.nbmh.marketing.service.INbmhCouponHistoryService;
import com.hszn.nbmh.marketing.service.INbmhCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhCouponServiceImpl extends ServiceImpl<NbmhCouponMapper, NbmhCoupon> implements INbmhCouponService {

    @Resource
    private INbmhCouponGoodsRelationService couponGoodsRelationService;

    @Resource
    private INbmhCouponCategoryRelationService couponCategoryRelationService;

    @Resource
    private INbmhCouponHistoryService couponHistoryService;

    @Override
    public boolean provide(CouponParam param) {
        SnowFlakeIdUtil idWorker = new SnowFlakeIdUtil(0L, 0);
        //优惠券ID
        Long id = idWorker.nextId();

        //构建分类关联
        List<NbmhCouponCategoryRelation> categoryRelation = new ArrayList<>(param.getCategoryList().size());
        param.getCategoryList().forEach(e -> categoryRelation.add(NbmhCouponCategoryRelation.builder().categoryId(e.getCategoryId()).categoryName(e.getCategoryName()).couponId(id).build()));

        //构建商品关联
        List<NbmhCouponGoodsRelation> goodsRelations = new ArrayList<>(param.getGoodsList().size());
        param.getGoodsList().forEach(e -> goodsRelations.add(NbmhCouponGoodsRelation.builder().goodsName(e.getGoodsName()).goodsId(e.getGoodsId()).couponId(id).build()));

        NbmhCoupon coupon = new NbmhCoupon();
        BeanUtils.copyProperties(param, coupon);
        //设置ID
        coupon.setId(id);

        //指定分类
        if (Objects.equals(param.getUseType(), 1)) {
            //保存分类关联
            couponCategoryRelationService.saveBatch(categoryRelation);

        }
        //指定商品
        if (Objects.equals(param.getUseType(), 2)) {
            //保存商品关联
            couponGoodsRelationService.saveBatch(goodsRelations);
        }
        this.save(coupon);
        return true;
    }

    @Override
    public boolean accept(Long couponId, Integer getType) {
        //检查是否可以领取,根据id和当前时间判断
        List<CouponAcceptOut> getAcceptHistory = getAcceptHistory(couponId);
        if (CollectionUtils.isEmpty(getAcceptHistory)) {
            return false;
        }

        //领取等级
        final CouponAcceptOut couponInfo = getAcceptHistory.get(0);
        Integer level = couponInfo.getUserLevel();
        //领取限制数量
        Integer perLimit = couponInfo.getPerLimit();
        AuthUser user = SecurityUtils.getUser();
        if (user == null) {
            throw new RuntimeException("获取用户信息失败");
        }
        Long userId = user.getId();
        int userLevel = 5;
        String userName = user.getName();
        //只有达到领取等级才继续 目前没有涉及用户等级
//        if (userLevel >= level) {
        //过滤userId,查看已领取数量
        long count = getAcceptHistory.stream().filter(e -> e.getUserId() != null).count();
        //没超过领取数量才继续
        if (count < perLimit) {
            NbmhCouponHistory history = NbmhCouponHistory.builder()
                    .couponId(couponInfo.getId())
                    .acquireType(getType)
                    .couponName(couponInfo.getCouponName())
                    .amount(couponInfo.getAmount())
                    .minPoint(couponInfo.getMinPoint())
                    .startTime(couponInfo.getStartTime())
                    .endTime(couponInfo.getEndTime())
                    .userId(userId)
                    .userName(userName)
                    .couponShopId(couponInfo.getShopId())
                    .couponUseType(couponInfo.getUseType())
                    //未使用
                    .status(0)
                    .build();
            couponHistoryService.save(history);
            return true;
        }
//        }
        return false;
    }

    @Override
    public List<CouponAcceptOut> getAcceptHistory(Long couponId) {
        SysAuthUser sysUser = SecurityUtils.getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("获取用户信息失败");
        }
        Long userId = sysUser.getId();
        return baseMapper.getAcceptHistory(couponId, userId);
    }

    @Override
    public List<NbmhCouponHistory> findCoupon(Integer status) {
        LambdaQueryWrapper<NbmhCouponHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NbmhCouponHistory::getStatus, status);
        return couponHistoryService.list(wrapper);
    }

    @Override
    public List<NbmhCouponHistory> findUsableCoupon(List<GoodsParam> params) {
        List<NbmhCouponHistory> result = new ArrayList<>();
        //根据用户和店铺id获取可用优惠券
        Set<Long> shopIds = params.stream().map(GoodsParam::getShopId).collect(Collectors.toSet());
        long userId = 10086L;
        List<NbmhCouponHistory> list = couponHistoryService.findUsableByShopId(shopIds, userId);
        //按类型分类,分三类处理
        Map<Integer, List<NbmhCouponHistory>> groupList = list.stream().collect(Collectors.groupingBy(NbmhCouponHistory::getCouponUseType));
        groupList.forEach((userType, histories) -> {
            //处理通用券,只要订单总额达到都可以使用
            if (userType == 0) {
                BigDecimal orderTotal = params.stream().map(GoodsParam::getTotalPrice).reduce(BigDecimal::add).orElse(new BigDecimal("0"));
                histories.forEach(e -> {
                    //店铺id为null表示为全场通用
                    if (e.getCouponShopId() == null) {
                        if (orderTotal.compareTo(e.getMinPoint()) > 0) {
                            result.add(e);
                        }
                    } else {
                        //店铺通用优惠券
                        BigDecimal shopTotal = params.stream().filter(o -> Objects.equals(o.getShopId(), e.getCouponShopId())).map(GoodsParam::getTotalPrice).reduce(BigDecimal::add).orElse(new BigDecimal("0"));
                        if (shopTotal.compareTo(e.getMinPoint()) > 0) {
                            result.add(e);
                        }
                    }
                });
            }

            //处理分类优惠券
            if (userType == 1) {
                histories.forEach(e -> {
                    //获取优惠券使用分类
                    String[] relations = e.getRelation().split(",");
                    List<String> relationList = new ArrayList<>(Arrays.asList(relations));
                    BigDecimal shopTotal = params.stream().filter(o -> Objects.equals(o.getShopId(), e.getCouponShopId())).filter(o -> relationList.contains(o.getCategoryId().toString())).map(GoodsParam::getTotalPrice).reduce(BigDecimal::add).orElse(new BigDecimal("0"));
                    if (shopTotal.compareTo(e.getMinPoint()) > 0) {
                        result.add(e);
                    }
                });
            }

            //处理商品优惠券
            if (userType == 2) {
                histories.forEach(e -> {
                    //获取优惠券使用分类
                    String[] relations = e.getRelation().split(",");
                    List<String> relationList = new ArrayList<>(Arrays.asList(relations));
                    BigDecimal shopTotal = params.stream().filter(o -> Objects.equals(o.getShopId(), e.getCouponShopId())).filter(o -> relationList.contains(o.getGoodsId().toString())).map(GoodsParam::getTotalPrice).reduce(BigDecimal::add).orElse(new BigDecimal("0"));
                    if (shopTotal.compareTo(e.getMinPoint()) > 0) {
                        result.add(e);
                    }
                });
            }
        });
        return result;
    }

    @Override
    public boolean updateCouponStatus(Long id, Integer status) {
        NbmhCouponHistory history = new NbmhCouponHistory();
        history.setStatus(status);
        history.setId(id);
        return couponHistoryService.updateById(history);
    }
}
