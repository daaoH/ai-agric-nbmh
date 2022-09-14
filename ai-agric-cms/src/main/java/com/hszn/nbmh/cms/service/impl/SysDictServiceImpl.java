package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.SysDict;
import com.hszn.nbmh.cms.mapper.SysDictMapper;
import com.hszn.nbmh.cms.service.ISysDictService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {


    @Override
    public IPage<SysDict> getByPage(QueryRequest<SysDict> param) {
        //添加条件
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getId())) {
                queryWrapper.eq(SysDict::getId, param.getQueryEntity().getId());
            }
            //状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(SysDict::getStatus, param.getQueryEntity().getStatus());
            }
            //农户
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getType())) {
                queryWrapper.eq(SysDict::getType, param.getQueryEntity().getType());
            }
            //时间
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                //时间 查询条件为年月日匹配
                if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                    String strStart = DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                    queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
                }
            }
        }
        //分页
        Page<SysDict> page = new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
