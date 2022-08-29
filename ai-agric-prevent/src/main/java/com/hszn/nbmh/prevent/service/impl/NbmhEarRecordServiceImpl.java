package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;
import com.hszn.nbmh.prevent.api.params.input.NbmhEarRecordParam;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.mapper.NbmhEarRecordMapper;
import com.hszn.nbmh.prevent.service.INbmhEarRecordService;
import com.hszn.nbmh.prevent.service.INbmhTradeReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 耳标补打信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhEarRecordServiceImpl extends ServiceImpl<NbmhEarRecordMapper, NbmhEarRecord> implements INbmhEarRecordService {
    @Resource
    private NbmhEarRecordMapper nbmhEarRecordMapper;

    @Resource
    private NbmhAnimalMapper animalMapper;

    @Autowired
    private INbmhTradeReportService tradeReportService;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhEarRecord> nbmhEarRecordList) {
        BeanUtils.validBean(nbmhEarRecordList, NbmhEarRecord.Save.class);

        return nbmhEarRecordList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            int ret = nbmhEarRecordMapper.insert(entity);

            handleAnimalData(entity);

            return ret;
        }).collect(Collectors.toList());
    }

    private void handleAnimalData(NbmhEarRecord entity) {

        List<NbmhTradeReport> nbmhTradeReportList = tradeReportService.list(NbmhTradeReport.builder().animalId(String.valueOf(entity.getAnimalId())).build(), null);
        if (nbmhTradeReportList != null && nbmhTradeReportList.size() > 0) {

            NbmhAnimal animal = animalMapper.selectById(entity.getAnimalId());
            String originalAnimalEarNo = animal.getEarNo();

            nbmhTradeReportList.forEach(tradeReport -> {

                String earNos = tradeReport.getEarNo().replaceAll(originalAnimalEarNo + ",*", "");

                if (earNos.endsWith(",")) {
                    earNos = earNos.substring(0, earNos.length() - 1);
                }

                tradeReport.setEarNo(earNos.equals("") ? null : earNos);
                tradeReport.setSuppleEarNo(entity.getEarNo());
            });

            tradeReportService.update(nbmhTradeReportList);
        }

        animalMapper.updateById(NbmhAnimal.builder().id(entity.getAnimalId()).earNo(entity.getEarNo()).build());

    }


    @Override
    @Transactional
    public int update(List<NbmhEarRecord> nbmhEarRecordList) {
        BeanUtils.validBean(nbmhEarRecordList, NbmhEarRecord.Update.class);

        if (nbmhEarRecordList == null || nbmhEarRecordList.size() == 0) {
            return 0;
        }

        return nbmhEarRecordList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            animalMapper.updateById(NbmhAnimal.builder().id(entity.getAnimalId()).earNo(entity.getEarNo()).build());

            return nbmhEarRecordMapper.update(entity, Wrappers.<NbmhEarRecord>lambdaUpdate().eq(NbmhEarRecord::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhEarRecord> query(NbmhEarRecordParam entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhEarRecord> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        NbmhEarRecord nbmhEarRecord = new NbmhEarRecord();
        org.springframework.beans.BeanUtils.copyProperties(entity, nbmhEarRecord);

        LambdaQueryWrapper<NbmhEarRecord> lambdaQueryWrapper = Wrappers.lambdaQuery(nbmhEarRecord);
        Date date = entity.getCreateTimeParam();
        if (ObjectUtils.isNotEmpty(date)) {
            String strStart = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss").substring(0, 7);
            lambdaQueryWrapper.apply("UNIX_TIMESTAMP(create_time) >= UNIX_TIMESTAMP('" + strStart + "-01')");
            lambdaQueryWrapper.apply("UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('" + strStart + "-31')");
        }

        return nbmhEarRecordMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhEarRecord> list(NbmhEarRecord entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhEarRecord> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhEarRecordMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhEarRecord entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhEarRecordMapper.updateById(entity);
            }
        });
    }

}
