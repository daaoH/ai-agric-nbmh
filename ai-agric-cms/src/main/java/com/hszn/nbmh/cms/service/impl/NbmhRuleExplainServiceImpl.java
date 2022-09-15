package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.hszn.nbmh.cms.mapper.NbmhRuleExplainMapper;
import com.hszn.nbmh.cms.service.INbmhRuleExplainService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 规则说明表 服务实现类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhRuleExplainServiceImpl extends ServiceImpl<NbmhRuleExplainMapper, NbmhRuleExplain> implements INbmhRuleExplainService {

    @Resource
    private NbmhRuleExplainMapper nbmhRuleExplainMapper;

    public IPage<NbmhRuleExplain> getByPage(QueryRequest<NbmhRuleExplain> nbmhRuleExplain) {


        //添加条件
        LambdaQueryWrapper<NbmhRuleExplain> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity())) {
            //id
            if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity().getId())) {
                queryWrapper.eq(NbmhRuleExplain::getId, nbmhRuleExplain.getQueryEntity().getId());
            }
            //状态
            if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhRuleExplain::getStatus, nbmhRuleExplain.getQueryEntity().getStatus());
            }
            //农户8
            if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity().getType())) {
                queryWrapper.eq(NbmhRuleExplain::getType, nbmhRuleExplain.getQueryEntity().getType());
            }
            //时间
            if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity().getCreateTime())) {
                //时间 查询条件为年月日匹配
                if (ObjectUtils.isNotEmpty(nbmhRuleExplain.getQueryEntity().getCreateTime())) {
                    String strStart = DateFormatUtils.format(nbmhRuleExplain.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                    queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
                }
            }
        }
        //分页
        Page<NbmhRuleExplain> page = new Page<>(nbmhRuleExplain.getPageNum(), nbmhRuleExplain.getPageSize());
        //排序
        SortUtil.handlePageSort(nbmhRuleExplain, page, nbmhRuleExplain.getField(), nbmhRuleExplain.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }


}
