package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhCheck;
import com.hszn.nbmh.prevent.api.params.input.CheckParam;
import com.hszn.nbmh.prevent.mapper.NbmhCheckMapper;
import com.hszn.nbmh.prevent.service.INbmhCheckService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 稽查上报 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-25
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class NbmhCheckServiceImpl extends ServiceImpl<NbmhCheckMapper, NbmhCheck> implements INbmhCheckService {

    @Override
    public IPage<NbmhCheck> getByPage(QueryRequest<CheckParam> param) {
        //添加条件
        LambdaQueryWrapper<NbmhCheck> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //稽查员
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCheckId())) {
                queryWrapper.eq(NbmhCheck::getCheckId, param.getQueryEntity().getCheckId());
            }
            //状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhCheck::getStatus, param.getQueryEntity().getStatus());
            }
        }
        //分页
        Page<NbmhCheck> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
