package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.mapper.NbmhAnimalDoctorDetailMapper;
import com.hszn.nbmh.user.service.INbmhAnimalDoctorDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * <p>
 * 兽医详细信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAnimalDoctorDetailServiceImpl extends ServiceImpl<NbmhAnimalDoctorDetailMapper, NbmhAnimalDoctorDetail> implements INbmhAnimalDoctorDetailService {
    @Resource
    private NbmhAnimalDoctorDetailMapper nbmhAnimalDoctorDetailMapper;

    private static Lock lock = new ReentrantLock();

    @Override
    @Transactional
    public List<Integer> save(List<NbmhAnimalDoctorDetail> nbmhAnimalDoctorDetailList) {

        return nbmhAnimalDoctorDetailList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhAnimalDoctorDetailMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhAnimalDoctorDetail> nbmhAnimalDoctorDetailList) {

        if (CollectionUtils.isEmpty(nbmhAnimalDoctorDetailList)) {
            return 0;
        }

        return nbmhAnimalDoctorDetailList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhAnimalDoctorDetailMapper.update(entity, Wrappers.<NbmhAnimalDoctorDetail>lambdaUpdate().eq(NbmhAnimalDoctorDetail::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhAnimalDoctorDetail> query(NbmhAnimalDoctorDetail entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhAnimalDoctorDetail> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAnimalDoctorDetail> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhAnimalDoctorDetailMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public IPage<NbmhAnimalDoctorDetail> query(NbmhAnimalDoctorDetail entity, int pageNum, int pageSize, Integer acceptOrderMinNum, Integer acceptOrderMaxNum, List<OrderItem> orderItemList) {
        Page<NbmhAnimalDoctorDetail> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAnimalDoctorDetail> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);
        if(acceptOrderMinNum != null && acceptOrderMaxNum != null){
            lambdaQueryWrapper.between(NbmhAnimalDoctorDetail::getAcceptOrderNum, acceptOrderMinNum, acceptOrderMaxNum);
        }

        return nbmhAnimalDoctorDetailMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public IPage<NbmhAnimalDoctorDetail> query(NbmhAnimalDoctorDetail entity, int pageNum, int pageSize, Integer acceptOrderMinNum, Integer acceptOrderMaxNum, double minX, double maxX, double minY, double maxY, List<OrderItem> orderItemList) {
        Page<NbmhAnimalDoctorDetail> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAnimalDoctorDetail> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);
        if(acceptOrderMinNum != null && acceptOrderMaxNum != null){
            lambdaQueryWrapper.between(NbmhAnimalDoctorDetail::getAcceptOrderNum, acceptOrderMinNum, acceptOrderMaxNum);
        }

        lambdaQueryWrapper.between(NbmhAnimalDoctorDetail::getLongitude, minX, maxX);

        lambdaQueryWrapper.between(NbmhAnimalDoctorDetail::getLatitude, minY, maxY);

        return nbmhAnimalDoctorDetailMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhAnimalDoctorDetail> list(NbmhAnimalDoctorDetail entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhAnimalDoctorDetail> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhAnimalDoctorDetailMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhAnimalDoctorDetail entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhAnimalDoctorDetailMapper.updateById(entity);
            }
        });
    }

    @Override
    @Transactional
    public int updateAcceptOrderNum(Long doctorId) {

        int ret;
        try {
            lock.lock();
            NbmhAnimalDoctorDetail animalDoctorDetail = nbmhAnimalDoctorDetailMapper.selectOne(Wrappers.lambdaQuery(NbmhAnimalDoctorDetail.builder().id(doctorId).build()));
            if (ObjectUtils.isEmpty(animalDoctorDetail)) {
                ret = 0;
            } else {
                animalDoctorDetail.setAcceptOrderNum(animalDoctorDetail.getAcceptOrderNum() + 1);
                ret = nbmhAnimalDoctorDetailMapper.updateById(animalDoctorDetail);
            }
        } finally {
            lock.unlock();
        }

        return ret;
    }

}
