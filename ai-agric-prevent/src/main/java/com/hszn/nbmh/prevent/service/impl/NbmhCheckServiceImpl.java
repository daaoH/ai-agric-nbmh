package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhCheck;
import com.hszn.nbmh.prevent.api.params.input.CheckParam;
import com.hszn.nbmh.prevent.api.params.input.CheckRecordParam;
import com.hszn.nbmh.prevent.api.params.out.CheckRecordDetailsResult;
import com.hszn.nbmh.prevent.mapper.NbmhCheckMapper;
import com.hszn.nbmh.prevent.service.INbmhCheckService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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


    /**
     * 获取防疫员举报记录
     *
     * @return
     * @Param List
     */
    @Override
    public List<CheckRecordDetailsResult> record(CheckRecordParam params) {

        List<CheckRecordDetailsResult> checkRecordDetailsResults=new ArrayList<>();

        //添加条件
        LambdaQueryWrapper<NbmhCheck> queryWrapper=new LambdaQueryWrapper<>();

        //防疫站id
        if (ObjectUtils.isNotEmpty(params.getPreventStationId())) {
            queryWrapper.eq(NbmhCheck::getPreventStationId, params.getPreventStationId());
        }
        //防疫站id
        if (ObjectUtils.isNotEmpty(params.getVaccinUserId())) {
            queryWrapper.eq(NbmhCheck::getCheckId, params.getVaccinUserId());
        }
        //时间 查询条件为年月日匹配
        if (ObjectUtils.isNotEmpty(params.getCheckDate())) {
            //时间 查询条件为年月日匹配
            String strStart=DateFormatUtils.format(params.getCheckDate(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        List<NbmhCheck> checks=this.baseMapper.selectList(queryWrapper);

        //数据分组
        Map<String, List<NbmhCheck>> groupMap=checks.stream()
                .collect(Collectors.groupingBy(u -> u.getUserId() + "|" + u.getAnimalType()));
        //分组处理数据
        for (Map.Entry<String, List<NbmhCheck>> entry : groupMap.entrySet()) {
            CheckRecordDetailsResult checkRecordDetailsResult=new CheckRecordDetailsResult();
            checkRecordDetailsResult.setUserName(entry.getValue().get(0).getUserName());
            checkRecordDetailsResult.setAnimaltype(entry.getValue().get(0).getAnimalType());
            checkRecordDetailsResult.setVaccinNum(entry.getValue().size());
            checkRecordDetailsResult.setCreateTime(entry.getValue().get(0).getCreateTime());
            checkRecordDetailsResults.add(checkRecordDetailsResult);
        }
        return checkRecordDetailsResults;
    }
}
