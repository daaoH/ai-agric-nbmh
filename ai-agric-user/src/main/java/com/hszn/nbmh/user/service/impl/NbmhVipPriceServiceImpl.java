package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.user.api.entity.NbmhVipPrice;
import com.hszn.nbmh.user.mapper.NbmhVipPriceMapper;
import com.hszn.nbmh.user.service.INbmhVipPriceService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户vip价格对照表 服务实现类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhVipPriceServiceImpl extends ServiceImpl<NbmhVipPriceMapper, NbmhVipPrice> implements INbmhVipPriceService {
    @Resource
    private NbmhVipPriceMapper nbmhVipPriceMapper;


    @Override
    public IPage<NbmhVipPrice> getByPage(QueryRequest<NbmhVipPrice> nbmhVipPrice) {
            //添加条件
            LambdaQueryWrapper<NbmhVipPrice> queryWrapper = new LambdaQueryWrapper<>();
            if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity())) {
                //id
                if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity().getId())) {
                    queryWrapper.eq(NbmhVipPrice::getId, nbmhVipPrice.getQueryEntity().getId());
                }
                //名称
                if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity().getTitle())) {
                    queryWrapper.eq(NbmhVipPrice::getTitle, nbmhVipPrice.getQueryEntity().getTitle());
                }
                //状态
                if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity().getStatus())) {
                    queryWrapper.eq(NbmhVipPrice::getStatus, nbmhVipPrice.getQueryEntity().getStatus());
                }
                //时间
                if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity().getCreateTime())) {
                    //时间 查询条件为年月日匹配
                    if (ObjectUtils.isNotEmpty(nbmhVipPrice.getQueryEntity().getCreateTime())) {
                        String strStart = DateFormatUtils.format(nbmhVipPrice.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                        queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
                    }
                }
            }
            //分页
            Page<NbmhVipPrice> page = new Page<>(nbmhVipPrice.getPageNum(), nbmhVipPrice.getPageSize());
            //排序
            SortUtil.handlePageSort(nbmhVipPrice, page, nbmhVipPrice.getField(), nbmhVipPrice.getOrder(), true);
            return baseMapper.selectPage(page, queryWrapper);

    }

    @Override
    public List<NbmhVipPrice> getByParam(NbmhVipPrice nbmhVipPrice) {

        //添加条件
        LambdaQueryWrapper<NbmhVipPrice> queryWrapper = new LambdaQueryWrapper<>();
        //等级
        if (ObjectUtils.isNotEmpty(nbmhVipPrice.getLevel())) {
            queryWrapper.eq(NbmhVipPrice::getLevel, nbmhVipPrice.getLevel());
        }
        //名称
        if (ObjectUtils.isNotEmpty(nbmhVipPrice.getTitle())) {
            queryWrapper.eq(NbmhVipPrice::getTitle, nbmhVipPrice.getTitle());
        }
        //价格
        if (ObjectUtils.isNotEmpty(nbmhVipPrice.getPrice())) {
            queryWrapper.eq(NbmhVipPrice::getPrice, nbmhVipPrice.getPrice());
        }
        //id
        if (ObjectUtils.isNotEmpty(nbmhVipPrice.getId())) {
            queryWrapper.eq(NbmhVipPrice::getId, nbmhVipPrice.getId());
        }
        //时间
        if (ObjectUtils.isNotEmpty(nbmhVipPrice.getCreateTime())) {
            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(nbmhVipPrice.getCreateTime())) {
                String strStart = DateFormatUtils.format(nbmhVipPrice.getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
            }
        }

        return baseMapper.selectList(queryWrapper);
    }
}