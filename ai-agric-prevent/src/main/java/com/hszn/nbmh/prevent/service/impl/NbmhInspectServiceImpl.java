package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.params.input.InspectRecordParam;
import com.hszn.nbmh.prevent.api.params.out.InspectRecordDetailsResult;
import com.hszn.nbmh.prevent.api.params.out.InspectRecordResult;
import com.hszn.nbmh.prevent.mapper.NbmhInspectMapper;
import com.hszn.nbmh.prevent.service.INbmhInspectService;

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
 * 检疫 检擦 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class NbmhInspectServiceImpl extends ServiceImpl<NbmhInspectMapper, NbmhInspect> implements INbmhInspectService {


    @Override
    public NbmhInspect getByEarNo(String earNo) {
        //添加条件
        LambdaQueryWrapper<NbmhInspect> queryWrapper=new LambdaQueryWrapper<>();
        //抵押状态
        if (ObjectUtils.isNotEmpty(earNo)) {
            queryWrapper.eq(NbmhInspect::getEarNo, earNo);
            return baseMapper.selectOne(queryWrapper);
        }
        return new NbmhInspect();
    }

    @Override
    public List<InspectRecordResult> record(InspectRecordParam inspectRecordParam) {
        //返回结果
        List<InspectRecordResult> results=new ArrayList<>();

        //添加条件
        LambdaQueryWrapper<NbmhInspect> queryWrapper=new LambdaQueryWrapper<>();
        //养殖户名字
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getUserName())) {
            queryWrapper.like(NbmhInspect::getUserName, inspectRecordParam.getUserName());
        }
        //防疫站id
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getPreventStationId())) {
            queryWrapper.eq(NbmhInspect::getPreventStationId, inspectRecordParam.getPreventStationId());
        }
        //检疫编号
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getReportNumber())) {
            queryWrapper.eq(NbmhInspect::getReportNumber, inspectRecordParam.getReportNumber());
        }
        //状态(1:未检疫,2:已检疫,3:数据失效,4:已举报)
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getStatus())) {
            queryWrapper.eq(NbmhInspect::getStatus, inspectRecordParam.getStatus());
        }
        //时间 查询条件为年月日匹配
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getInspectDate())) {
            //时间 查询条件为年月日匹配
            String strStart=DateFormatUtils.format(inspectRecordParam.getInspectDate(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        //获取数据集
        List<NbmhInspect> inspects=this.baseMapper.selectList(queryWrapper);
        Map<String, List<NbmhInspect>> groupMap=inspects.stream()
                .collect(Collectors.groupingBy(inspect -> inspect.getUserId() + "|" + inspect.getAnimalType()));

        //分组处理数据
        for (Map.Entry<String, List<NbmhInspect>> entry : groupMap.entrySet()) {
            InspectRecordResult inspectRecordResult=new InspectRecordResult();
            inspectRecordResult.setUserId(entry.getValue().get(0).getUserId());
            inspectRecordResult.setUserPhone(entry.getValue().get(0).getUserPhone());
            inspectRecordResult.setUserName(entry.getValue().get(0).getUserName());
            inspectRecordResult.setAnimalType(entry.getValue().get(0).getAnimalType());
            inspectRecordResult.setInspectDate(entry.getValue().get(0).getCreateTime());
            inspectRecordResult.setNum(entry.getValue().size());
            results.add(inspectRecordResult);
        }
        return results;
    }

    @Override
    public InspectRecordDetailsResult recordDetails(InspectRecordParam inspectRecordParam) {

        //返回结果
        InspectRecordDetailsResult result=new InspectRecordDetailsResult();
        //添加条件
        LambdaQueryWrapper<NbmhInspect> queryWrapper=new LambdaQueryWrapper<>();
        //养殖户
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getUserId())) {
            queryWrapper.eq(NbmhInspect::getUserId, inspectRecordParam.getUserId());
        }
        //状态(1:未检疫,2:已检疫,3:数据失效,4:已举报)
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getAnimalType())) {
            queryWrapper.eq(NbmhInspect::getAnimalType, inspectRecordParam.getAnimalType());
        }
        //时间 查询条件为年月日匹配
        if (ObjectUtils.isNotEmpty(inspectRecordParam.getInspectDate())) {
            String strStart=DateFormatUtils.format(inspectRecordParam.getInspectDate(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        //获取数据集
        List<NbmhInspect> inspectsList=this.baseMapper.selectList(queryWrapper);
        List<String> earNos=new ArrayList<>();
        inspectsList.forEach(inspect -> {
            result.builder()
                    .animalType(inspect.getAnimalType()).buyerCard(inspect.getBuyerCard())
                    .buyerName(inspect.getBuyerName()).buyerPhone(inspect.getBuyerPhone())
                    .createTime(inspect.getCreateTime()).destination(inspect.getDestination())
                    .placeConsigned(inspect.getPlaceConsigned()).inspectNumber(inspect.getInspectNumber())
                    .inspectProveUrl(inspect.getInspectProveUrl()).submitBy(inspect.getSubmitBy())
                    .submitByPhone(inspect.getSubmitByPhone()).userAvatarUrl(inspect.getUserAvatarUrl()).userName(inspect.getUserName())
                    .build();
            earNos.add(inspect.getEarNo());
        });
        result.setNum(inspectsList.size());
        result.setEarNos(earNos);
        return result;
    }
}
