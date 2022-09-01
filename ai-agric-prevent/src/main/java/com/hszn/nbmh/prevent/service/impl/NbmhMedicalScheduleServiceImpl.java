package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.mapper.NbmhMedicalScheduleMapper;
import com.hszn.nbmh.prevent.service.INbmhMedicalScheduleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 接诊时间设置 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhMedicalScheduleServiceImpl extends ServiceImpl<NbmhMedicalScheduleMapper, NbmhMedicalSchedule> implements INbmhMedicalScheduleService {
    @Resource
    private NbmhMedicalScheduleMapper nbmhMedicalScheduleMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhMedicalSchedule> nbmhMedicalScheduleList) {
        BeanUtils.validBean(nbmhMedicalScheduleList, NbmhMedicalSchedule.Save.class);

        return nbmhMedicalScheduleList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhMedicalScheduleMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhMedicalSchedule> nbmhMedicalScheduleList) {
        BeanUtils.validBean(nbmhMedicalScheduleList, NbmhMedicalSchedule.Update.class);

        if (CollectionUtils.isEmpty(nbmhMedicalScheduleList)) {
            return 0;
        }

        return nbmhMedicalScheduleList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhMedicalScheduleMapper.update(entity, Wrappers.<NbmhMedicalSchedule>lambdaUpdate().eq(NbmhMedicalSchedule::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhMedicalSchedule> query(NbmhMedicalSchedule entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhMedicalSchedule> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhMedicalSchedule> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhMedicalScheduleMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhMedicalSchedule> list(NbmhMedicalSchedule entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhMedicalSchedule> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhMedicalScheduleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhMedicalSchedule entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhMedicalScheduleMapper.updateById(entity);
            }
        });
    }

}
