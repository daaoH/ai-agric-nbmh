package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescription;
import com.hszn.nbmh.prevent.mapper.NbmhPrescriptionMapper;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionService;
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
 * 处方基础信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhPrescriptionServiceImpl extends ServiceImpl<NbmhPrescriptionMapper, NbmhPrescription> implements INbmhPrescriptionService {
    @Resource
    private NbmhPrescriptionMapper nbmhPrescriptionMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhPrescription> nbmhPrescriptionList) {
        BeanUtils.validBean(nbmhPrescriptionList, NbmhPrescription.Save.class);

        return nbmhPrescriptionList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhPrescriptionMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhPrescription> nbmhPrescriptionList) {
        BeanUtils.validBean(nbmhPrescriptionList, NbmhPrescription.Update.class);

        if (CollectionUtils.isEmpty(nbmhPrescriptionList)) {
            return 0;
        }

        return nbmhPrescriptionList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhPrescriptionMapper.update(entity, Wrappers.<NbmhPrescription>lambdaUpdate().eq(NbmhPrescription::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhPrescription> query(NbmhPrescription entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhPrescription> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhPrescription> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhPrescriptionMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhPrescription> list(NbmhPrescription entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhPrescription> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhPrescriptionMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhPrescription entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhPrescriptionMapper.updateById(entity);
            }
        });
    }

}
