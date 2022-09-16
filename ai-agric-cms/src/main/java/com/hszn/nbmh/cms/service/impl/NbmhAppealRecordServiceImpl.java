package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhAppealRecord;
import com.hszn.nbmh.cms.mapper.NbmhAppealRecordMapper;
import com.hszn.nbmh.cms.service.INbmhAppealRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 申诉记录表 服务实现类
 * </p>
 *
 * @author pyq
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAppealRecordServiceImpl extends ServiceImpl<NbmhAppealRecordMapper, NbmhAppealRecord> implements INbmhAppealRecordService {
    @Resource
    private NbmhAppealRecordMapper nbmhAppealRecordMapper;

    @Override
    public List<Integer> save(List<NbmhAppealRecord> nbmhAppealRecordList) {

        return nbmhAppealRecordList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhAppealRecordMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhAppealRecord> nbmhAppealRecordList) {

        if (nbmhAppealRecordList == null || nbmhAppealRecordList.size() == 0) {
            return 0;
        }

        return nbmhAppealRecordList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhAppealRecordMapper.update(entity, Wrappers.<NbmhAppealRecord>lambdaUpdate().eq(NbmhAppealRecord::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhAppealRecord> query(NbmhAppealRecord entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhAppealRecord> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAppealRecord> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhAppealRecordMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhAppealRecord> list(NbmhAppealRecord entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhAppealRecord> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhAppealRecordMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhAppealRecord entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhAppealRecordMapper.updateById(entity);
            }
        });
    }


}
