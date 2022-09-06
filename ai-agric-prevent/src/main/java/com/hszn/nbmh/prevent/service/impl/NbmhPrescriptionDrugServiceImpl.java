package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import com.hszn.nbmh.prevent.mapper.NbmhPrescriptionDrugMapper;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionDrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 处方药品列表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhPrescriptionDrugServiceImpl extends ServiceImpl<NbmhPrescriptionDrugMapper, NbmhPrescriptionDrug> implements INbmhPrescriptionDrugService {
    @Resource
    private NbmhPrescriptionDrugMapper nbmhPrescriptionDrugMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhPrescriptionDrug> NbmhPrescriptionDrugList) {
        BeanUtils.validBean(NbmhPrescriptionDrugList, NbmhPrescriptionDrug.Save.class);

        return NbmhPrescriptionDrugList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhPrescriptionDrugMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhPrescriptionDrug> NbmhPrescriptionDrugList) {
        BeanUtils.validBean(NbmhPrescriptionDrugList, NbmhPrescriptionDrug.Update.class);

        if (CollectionUtils.isEmpty(NbmhPrescriptionDrugList)) {
            return 0;
        }

        return NbmhPrescriptionDrugList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhPrescriptionDrugMapper.update(entity, Wrappers.<NbmhPrescriptionDrug>lambdaUpdate().eq(NbmhPrescriptionDrug::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhPrescriptionDrug> query(NbmhPrescriptionDrug entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhPrescriptionDrug> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhPrescriptionDrug> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhPrescriptionDrugMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhPrescriptionDrug> list(NbmhPrescriptionDrug entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhPrescriptionDrug> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhPrescriptionDrugMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhPrescriptionDrug entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhPrescriptionDrugMapper.updateById(entity);
            }
        });
    }

}
