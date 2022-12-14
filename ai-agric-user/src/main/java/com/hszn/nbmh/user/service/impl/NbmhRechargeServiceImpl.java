package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.user.api.entity.NbmhRecharge;
import com.hszn.nbmh.user.mapper.NbmhRechargeMapper;
import com.hszn.nbmh.user.service.INbmhRechargeService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhRechargeServiceImpl extends ServiceImpl<NbmhRechargeMapper, NbmhRecharge> implements INbmhRechargeService {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhVaccin>
     */
    @Override
    public IPage<NbmhRecharge> getByPage(QueryRequest<NbmhRecharge> param) {
        //添加条件
        LambdaQueryWrapper<NbmhRecharge> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //用户id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhRecharge::getUserId, param.getQueryEntity().getUserId());
            }
            //用户模糊
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserName())) {
                queryWrapper.like(NbmhRecharge::getUserName, param.getQueryEntity().getUserName());
            }
            //第三方流水号
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getTransactionId())) {
                queryWrapper.like(NbmhRecharge::getTransactionId, param.getQueryEntity().getTransactionId());
            }
            //状态 -1 未通过 0 审核中 1 已提现
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhRecharge::getStatus, param.getQueryEntity().getStatus());
            }
            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            }
        }
        //分页
        Page<NbmhRecharge> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }


}
