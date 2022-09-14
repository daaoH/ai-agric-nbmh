package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.user.api.entity.NbmhUserCoinRecord;
import com.hszn.nbmh.user.mapper.NbmhUserCoinRecordMapper;
import com.hszn.nbmh.user.service.INbmhUserCoinRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 农牧币记录信息 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-14
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhUserCoinRecordServiceImpl extends ServiceImpl<NbmhUserCoinRecordMapper, NbmhUserCoinRecord> implements INbmhUserCoinRecordService {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhVaccin>
     */
    @Override
    public IPage<NbmhUserCoinRecord> getByPage(QueryRequest<NbmhUserCoinRecord> param) {
        //添加条件
        LambdaQueryWrapper<NbmhUserCoinRecord> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //用户id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhUserCoinRecord::getUserId, param.getQueryEntity().getUserId());
            }
            //业务id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getBizId())) {
                queryWrapper.like(NbmhUserCoinRecord::getBizId, param.getQueryEntity().getBizId());
            }
            //是否支出
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getIsIncome())) {
                queryWrapper.eq(NbmhUserCoinRecord::getIsIncome, param.getQueryEntity().getIsIncome());
            }
            //业务类型
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getBizType())) {
                queryWrapper.eq(NbmhUserCoinRecord::getBizType, param.getQueryEntity().getBizType());
            }
            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            }
        }
        //分页
        Page<NbmhUserCoinRecord> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }


}
