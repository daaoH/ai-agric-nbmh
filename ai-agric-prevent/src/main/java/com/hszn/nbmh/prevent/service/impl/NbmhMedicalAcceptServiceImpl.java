package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.mapper.NbmhMedicalAcceptMapper;
import com.hszn.nbmh.prevent.service.INbmhMedicalAcceptService;
import com.hszn.nbmh.user.api.feign.RemoteAnimalDoctorDetailService;
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
 * 动物诊疗接单记录 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhMedicalAcceptServiceImpl extends ServiceImpl<NbmhMedicalAcceptMapper, NbmhMedicalAccept> implements INbmhMedicalAcceptService {
    @Resource
    private NbmhMedicalAcceptMapper nbmhMedicalAcceptMapper;

    @Resource
    private RemoteAnimalDoctorDetailService remoteAnimalDoctorDetailService;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhMedicalAccept> nbmhMedicalAcceptList) {

        return nbmhMedicalAcceptList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhMedicalAcceptMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhMedicalAccept> nbmhMedicalAcceptList) {

        if (CollectionUtils.isEmpty(nbmhMedicalAcceptList)) {
            return 0;
        }

        return nbmhMedicalAcceptList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhMedicalAcceptMapper.update(entity, Wrappers.<NbmhMedicalAccept>lambdaUpdate().eq(NbmhMedicalAccept::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional
    public int acceptOrder(List<NbmhMedicalAccept> nbmhMedicalAcceptList) {

        if (CollectionUtils.isEmpty(nbmhMedicalAcceptList)) {
            return 0;
        }

        return nbmhMedicalAcceptList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            remoteAnimalDoctorDetailService.updateAcceptOrderNum(entity.getDoctorId());

            return nbmhMedicalAcceptMapper.update(entity, Wrappers.<NbmhMedicalAccept>lambdaUpdate().eq(NbmhMedicalAccept::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhMedicalAccept> query(NbmhMedicalAccept entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhMedicalAccept> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhMedicalAccept> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhMedicalAcceptMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhMedicalAccept> list(NbmhMedicalAccept entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhMedicalAccept> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhMedicalAcceptMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhMedicalAccept entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhMedicalAcceptMapper.updateById(entity);
            }
        });
    }

}
