package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.hszn.nbmh.cms.api.entity.NbmhViolationRecord;
import com.hszn.nbmh.cms.mapper.NbmhViolationRecordMapper;
import com.hszn.nbmh.cms.service.INbmhViolationRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 违规记录表 服务实现类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhViolationRecordServiceImpl extends ServiceImpl<NbmhViolationRecordMapper, NbmhViolationRecord> implements INbmhViolationRecordService {
    @Resource
    private NbmhViolationRecordMapper nbmhViolationRecordMapper;
    @Override
    public IPage<NbmhViolationRecord> getByPage(QueryRequest<NbmhViolationRecord> nbmhViolationRecord) {
        //添加条件
        LambdaQueryWrapper<NbmhViolationRecord> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity())) {
            //id
            if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity().getId())) {
                queryWrapper.eq(NbmhViolationRecord::getId, nbmhViolationRecord.getQueryEntity().getId());
            }
            //状态
            if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhViolationRecord::getStatus, nbmhViolationRecord.getQueryEntity().getStatus());
            }
            //农户8
            if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity().getType())) {
                queryWrapper.eq(NbmhViolationRecord::getType, nbmhViolationRecord.getQueryEntity().getType());
            }
            //时间
            if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity().getCreateTime())) {
                //时间 查询条件为年月日匹配
                if (ObjectUtils.isNotEmpty(nbmhViolationRecord.getQueryEntity().getCreateTime())) {
                    String strStart = DateFormatUtils.format(nbmhViolationRecord.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                    queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
                }
            }
        }
        //分页
        Page<NbmhViolationRecord> page = new Page<>(nbmhViolationRecord.getPageNum(), nbmhViolationRecord.getPageSize());
        //排序
        SortUtil.handlePageSort(nbmhViolationRecord, page, nbmhViolationRecord.getField(), nbmhViolationRecord.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }

}
