package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.params.input.UserIntegralRecordParam;
import com.hszn.nbmh.prevent.api.params.out.*;
import com.hszn.nbmh.prevent.mapper.NbmhUserIntegralRecordMapper;
import com.hszn.nbmh.prevent.service.INbmhUserIntegralRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 积分记录信息 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-22
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhUserIntegralRecordServiceImpl extends ServiceImpl<NbmhUserIntegralRecordMapper, NbmhUserIntegralRecord> implements INbmhUserIntegralRecordService {


    /**
     * 根据参数获取积分记录数据集合
     *
     * @return List<NbmhUserIntegralRecord>
     * @Param param
     */
    public List<NbmhUserIntegralRecord> getByParam(UserIntegralRecordParam param) {
        //添加条件
        LambdaQueryWrapper<NbmhUserIntegralRecord> queryWrapper=new LambdaQueryWrapper<>();
        //防疫-检疫人-id
        if (ObjectUtils.isNotEmpty(param.getVaccinUserId())) {
            queryWrapper.eq(NbmhUserIntegralRecord::getVaccinId, param.getVaccinUserId());
        }
        //防疫站id
        if (ObjectUtils.isNotEmpty(param.getPreventStationId())) {
            queryWrapper.eq(NbmhUserIntegralRecord::getPreventStationId, param.getPreventStationId());
        }
        //是否为收入(0:false,1:true)
        if (ObjectUtils.isNotEmpty(param.getIsIncome())) {
            queryWrapper.eq(NbmhUserIntegralRecord::getIsIncome, param.getIsIncome());
        }
        //动物类型(种类 0猪 1牛)
        if (ObjectUtils.isNotEmpty(param.getAnimalType())) {
            queryWrapper.eq(NbmhUserIntegralRecord::getAnimalType, param.getAnimalType());
        }
        //来源(1:防疫,2:检疫,3:分账)
        if (ObjectUtils.isNotEmpty(param.getSource())) {
            queryWrapper.eq(NbmhUserIntegralRecord::getSource, param.getSource());
        }
        //时间 查询条件为年月匹配
        if (ObjectUtils.isNotEmpty(param.getYearsAndMonth())) {
            //时间 查询条件为年月日匹配
            String strStart=DateFormatUtils.format(param.getYearsAndMonth(), "yyyy-MM-dd");
            queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 防疫员管理详情-积分获取列表
     *
     * @param param
     * @return List<IntegralRecordDetailsResult>
     */
    @Override
    public List<IntegralRecordDetailsResult> integralRecordDetails(UserIntegralRecordParam param) {
        //返回结果
        List<IntegralRecordDetailsResult> integralRecordDetailsResults=new ArrayList<>();
        //获取数据集
        Map<Integer, List<NbmhUserIntegralRecord>> groupMap=this.getByParam(param).stream()
                .collect(Collectors.groupingBy(u -> u.getSource()));
        //分组处理数据
        for (Map.Entry<Integer, List<NbmhUserIntegralRecord>> entry : groupMap.entrySet()) {
            IntegralRecordDetailsResult integralRecordDetailsResult=new IntegralRecordDetailsResult();
            //1邀请 2动物防疫 3动物检疫
            if (entry.getValue().get(0).getSource() == 1) {
                integralRecordDetailsResult.setUserName("邀请养殖户" + entry.getValue().get(0).getUserName() + ".......");
                integralRecordDetailsResult.setType(1);
            } else {
                integralRecordDetailsResult.setType(2);
                //动物类型(种类 0猪 1牛)
//                integralRecordDetailsResult.setAnimaltype(entry.getValue().get(0).getAnimalType());
            }
//            else {
//                //动物类型(种类 0猪 1牛)
//                integralRecordDetailsResult.setAnimaltype(entry.getValue().get(0).getAnimalType());
//                integralRecordDetailsResult.setType(3);
//            }
            integralRecordDetailsResult.setCreateTime(entry.getValue().get(0).getCreateTime());
            integralRecordDetailsResult.setVaccinNum(entry.getValue().size());
            integralRecordDetailsResult.setIntegral(entry.getValue().stream().mapToInt(NbmhUserIntegralRecord -> NbmhUserIntegralRecord.getIntegral()).sum());
            integralRecordDetailsResults.add(integralRecordDetailsResult);
        }
        return integralRecordDetailsResults;
    }

    /**
     * 积分记录
     *
     * @param param
     * @return List<IntegralRecordResult>
     */
    @Override
    public IntegralRecordInfoResult integralRecord(UserIntegralRecordParam param) {
        //返回结果
        IntegralRecordInfoResult integralRecordInfoResult=new IntegralRecordInfoResult();
        //返回列表数据
        List<IntegralRecordResult> integralRecordResults=new ArrayList<>();
        //获取数据集
        List<NbmhUserIntegralRecord> nbmhUserIntegralRecords=this.getByParam(param);
        Map<String, List<NbmhUserIntegralRecord>> groupMap=nbmhUserIntegralRecords.stream()
                .collect(Collectors.groupingBy(u -> u.getUserId() + "|" + u.getSource() + "|" + u.getAnimalType()));

        //TODO 消耗(积分兑换数据)


        //分组处理数据
        for (Map.Entry<String, List<NbmhUserIntegralRecord>> entry : groupMap.entrySet()) {
            IntegralRecordResult integralRecordResult=new IntegralRecordResult();
            integralRecordResult.setIntegral(entry.getValue().stream().mapToInt(NbmhUserIntegralRecord -> NbmhUserIntegralRecord.getIntegral()).sum());
            integralRecordResult.setUserAvatarUrl(entry.getValue().get(0).getUserAvatarUrl());
            String source="";
            //来源(1:邀请 2:防疫,3:检疫,3:分账)
            if (entry.getValue().get(0).getSource() == 1) {
                source="邀请";
            } else if (entry.getValue().get(0).getSource() == 2) {
                source="防疫";
            } else {
                source="未知";
            }
            String animalType="";
            // 0猪 1牛
            if (entry.getValue().get(0).getAnimalType() == 0) {
                animalType="猪";
            } else {
                animalType="牛";
            }
            int num=entry.getValue().size();
            String describ=entry.getValue().get(0).getUserName() + source + num + "头" + animalType;
            integralRecordResult.setDescribe(describ);
            integralRecordResults.add(integralRecordResult);
        }
        integralRecordInfoResult.setIntegralRecordResults(integralRecordResults);
        integralRecordInfoResult.setTotalIntegral(integralRecordResults.stream().mapToInt(IntegralRecordResult -> IntegralRecordResult.getIntegral()).sum());
        return integralRecordInfoResult;
    }

    /**
     * 防疫员积分获取记录
     *
     * @param param
     * @return List<VUserIntegralRecordResult>
     */
    @Override
    public VUserIntegralRecordResult vUserIntegralRecordResult(UserIntegralRecordParam param) {
        //返回结果
        VUserIntegralRecordResult vUserIntegralRecordResult=new VUserIntegralRecordResult();
        //获取数据集
        List<NbmhUserIntegralRecord> nbmhUserIntegralRecords=this.getByParam(param);
        Map<Long, List<NbmhUserIntegralRecord>> groupMap=nbmhUserIntegralRecords.stream().collect(Collectors.groupingBy(NbmhUserIntegralRecord::getUserId));
        vUserIntegralRecordResult.setTotalVaccinNum(nbmhUserIntegralRecords.size());
        vUserIntegralRecordResult.setYearsAndMonth(param.getYearsAndMonth());
        vUserIntegralRecordResult.setTotalIntegral(nbmhUserIntegralRecords.stream().mapToInt(NbmhUserIntegralRecord -> NbmhUserIntegralRecord.getIntegral()).sum());
        List<VUserIntegralRecordListResult> vUserIntegralRecordListResultList=new ArrayList<>();
        if (param.getSource() == 2) {
            //分组处理数据
            for (Map.Entry<Long, List<NbmhUserIntegralRecord>> entry : groupMap.entrySet()) {
                VUserIntegralRecordListResult vUserIntegralRecordListResult=new VUserIntegralRecordListResult();
                vUserIntegralRecordListResult.setVaccinNum(entry.getValue().size());
                vUserIntegralRecordListResult.setIntegral(entry.getValue().stream().mapToInt(NbmhUserIntegralRecord -> NbmhUserIntegralRecord.getIntegral()).sum());
                vUserIntegralRecordListResult.setUserAvatarUrl(entry.getValue().get(0).getUserAvatarUrl());
                vUserIntegralRecordListResult.setUserName(entry.getValue().get(0).getUserName());
                List<String> earNos=new ArrayList<>();
                for (NbmhUserIntegralRecord nb : entry.getValue()) {
                    earNos.add(nb.getEarNo());
                }
                vUserIntegralRecordListResult.setEarNos(earNos);
                vUserIntegralRecordListResultList.add(vUserIntegralRecordListResult);
            }
        } else {
            //分组处理数据
            for (Map.Entry<Long, List<NbmhUserIntegralRecord>> entry : groupMap.entrySet()) {
                VUserIntegralRecordListResult vUserIntegralRecordListResult=new VUserIntegralRecordListResult();
                vUserIntegralRecordListResult.setVaccinNum(0);
                vUserIntegralRecordListResult.setIntegral(entry.getValue().stream().mapToInt(NbmhUserIntegralRecord -> NbmhUserIntegralRecord.getIntegral()).sum());
                vUserIntegralRecordListResult.setUserAvatarUrl(entry.getValue().get(0).getUserAvatarUrl());
                vUserIntegralRecordListResult.setUserName(entry.getValue().get(0).getUserName());
                vUserIntegralRecordListResult.setEarNos(new ArrayList<>());
                vUserIntegralRecordListResultList.add(vUserIntegralRecordListResult);
            }
        }
        vUserIntegralRecordResult.setVUserIntegralRecordListResultList(vUserIntegralRecordListResultList);
        return vUserIntegralRecordResult;
    }
}
