package com.hszn.nbmh.third.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.hszn.nbmh.third.mapper.NbmhBaseConfigMapper;
import com.hszn.nbmh.third.service.INbmhBaseConfigService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 全局配置表 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-26
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhBaseConfigServiceImpl extends ServiceImpl<NbmhBaseConfigMapper, NbmhBaseConfig> implements INbmhBaseConfigService {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhBaseConfig>
     */
    @Override
    public IPage<NbmhBaseConfig> getByPage(QueryRequest<NbmhBaseConfig> param) {
        //添加条件
        LambdaQueryWrapper<NbmhBaseConfig> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //农户
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getConfigKey())) {
                queryWrapper.eq(NbmhBaseConfig::getConfigKey, param.getQueryEntity().getConfigKey());
            }
            //防疫人员
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getId())) {
                queryWrapper.eq(NbmhBaseConfig::getId, param.getQueryEntity().getId());
            }
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getSubject())) {
                queryWrapper.eq(NbmhBaseConfig::getSubject, param.getQueryEntity().getSubject());
            }
            //牲畜类型
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getConfigValue())) {
                queryWrapper.eq(NbmhBaseConfig::getConfigValue, param.getQueryEntity().getConfigValue());
            }

            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
            }
        }
        //分页
        Page<NbmhBaseConfig> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<NbmhBaseConfig> getBySubject(String sbject) {
        //添加条件
        return baseMapper.selectList(Wrappers.<NbmhBaseConfig>query().lambda().eq(NbmhBaseConfig::getSubject, sbject));
    }


}
