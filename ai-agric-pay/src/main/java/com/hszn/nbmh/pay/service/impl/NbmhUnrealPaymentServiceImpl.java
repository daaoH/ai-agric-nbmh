package com.hszn.nbmh.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;
import com.hszn.nbmh.pay.mapper.NbmhUnrealPaymentMapper;
import com.hszn.nbmh.pay.service.INbmhUnrealPaymentService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
 * 虚拟支付信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-13
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUnrealPaymentServiceImpl extends ServiceImpl<NbmhUnrealPaymentMapper, NbmhUnrealPayment> implements INbmhUnrealPaymentService {
    @Resource
    private NbmhUnrealPaymentMapper nbmhUnrealPaymentMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhUnrealPayment> nbmhUnrealPaymentList) {

        return nbmhUnrealPaymentList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(0);

            return nbmhUnrealPaymentMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhUnrealPayment> nbmhUnrealPaymentList) {

        if (CollectionUtils.isEmpty(nbmhUnrealPaymentList)) {
            return 0;
        }

        return nbmhUnrealPaymentList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhUnrealPaymentMapper.update(entity, Wrappers.<NbmhUnrealPayment>lambdaUpdate().eq(NbmhUnrealPayment::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhUnrealPayment> query(NbmhUnrealPayment entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhUnrealPayment> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhUnrealPayment> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhUnrealPaymentMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhUnrealPayment> list(NbmhUnrealPayment entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhUnrealPayment> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhUnrealPaymentMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhUnrealPayment entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhUnrealPaymentMapper.updateById(entity);
            }
        });
    }

}
