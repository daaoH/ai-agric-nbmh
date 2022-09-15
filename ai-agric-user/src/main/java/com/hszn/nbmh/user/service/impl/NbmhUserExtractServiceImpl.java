package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.user.api.entity.NbmhUserExtract;
import com.hszn.nbmh.user.mapper.NbmhUserExtractMapper;
import com.hszn.nbmh.user.service.INbmhUserExtractService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 用户提现表 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhUserExtractServiceImpl extends ServiceImpl<NbmhUserExtractMapper, NbmhUserExtract> implements INbmhUserExtractService {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhVaccin>
     */
    @Override
    public IPage<NbmhUserExtract> getByPage(QueryRequest<NbmhUserExtract> param) {
        //添加条件
        LambdaQueryWrapper<NbmhUserExtract> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //用户id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhUserExtract::getUserId, param.getQueryEntity().getUserId());
            }
            //用户名
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getRealName())) {
                queryWrapper.like(NbmhUserExtract::getRealName, param.getQueryEntity().getRealName());
            }
            //bank = 银行卡 alipay = 支付宝 wx=微信
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getExtractType())) {
                queryWrapper.eq(NbmhUserExtract::getExtractType, param.getQueryEntity().getExtractType());
            }
            //状态 -1 未通过 0 审核中 1 已提现
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhUserExtract::getStatus, param.getQueryEntity().getStatus());
            }

            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            }
        }
        //分页
        Page<NbmhUserExtract> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }


}
