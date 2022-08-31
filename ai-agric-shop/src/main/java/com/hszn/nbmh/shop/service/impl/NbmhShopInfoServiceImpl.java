package com.hszn.nbmh.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.shop.api.params.input.ShopInfoParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoSearchParam;
import com.hszn.nbmh.shop.mapper.NbmhShopInfoMapper;
import com.hszn.nbmh.shop.service.INbmhShopInfoService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 店铺信息表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhShopInfoServiceImpl extends ServiceImpl<NbmhShopInfoMapper, NbmhShopInfo> implements INbmhShopInfoService {

    @Override
    public boolean saveShop(ShopInfoParam shopInfo) {
        NbmhShopInfo info = new NbmhShopInfo();
        BeanUtils.copyProperties(shopInfo, info);
        //新开店铺为审核状态
        info.setStatus(0);
        return save(info);
    }

    @Override
    public Page<NbmhShopInfo> search(QueryRequest<ShopInfoSearchParam> param) {
        NbmhShopInfo info = new NbmhShopInfo();
        BeanUtils.copyProperties(param.getQueryEntity(), info);
        Page<NbmhShopInfo> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        QueryWrapper<NbmhShopInfo> wrapper = Wrappers.query(info);
        return page(page, wrapper);
    }
}
