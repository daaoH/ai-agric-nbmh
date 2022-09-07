package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.params.input.NbmhMedicalOrderParam;
import com.hszn.nbmh.prevent.mapper.NbmhMedicalOrderMapper;
import com.hszn.nbmh.prevent.service.INbmhMedicalAcceptService;
import com.hszn.nbmh.prevent.service.INbmhMedicalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 诊断下单记录 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhMedicalOrderServiceImpl extends ServiceImpl<NbmhMedicalOrderMapper, NbmhMedicalOrder> implements INbmhMedicalOrderService {
    @Resource
    private NbmhMedicalOrderMapper nbmhMedicalOrderMapper;

    @Autowired
    private INbmhMedicalAcceptService nbmhMedicalAcceptService;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhMedicalOrderParam> nbmhMedicalOrderParamList) {

        return nbmhMedicalOrderParamList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setId(new SnowFlakeIdUtil(1, 0).nextId()).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            NbmhMedicalOrder nbmhMedicalOrder = new NbmhMedicalOrder();
            BeanUtils.copyProperties(entity, nbmhMedicalOrder);

            int ret = nbmhMedicalOrderMapper.insert(nbmhMedicalOrder);

            List<NbmhMedicalAccept> medicalAcceptList = entity.getMedicalAcceptList();
            if (CollectionUtils.isEmpty(medicalAcceptList)) {
                throw new ServiceException("未指定接诊专家，无法下单");
            }

            medicalAcceptList.forEach(item -> item.setMedicalOrderNumber(entity.getId()).setOrderStatus(0).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1));

            nbmhMedicalAcceptService.saveBatch(medicalAcceptList);

            return ret;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhMedicalOrder> nbmhMedicalOrderList) {
        if (CollectionUtils.isEmpty(nbmhMedicalOrderList)) {
            return 0;
        }

        return nbmhMedicalOrderList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhMedicalOrderMapper.update(entity, Wrappers.<NbmhMedicalOrder>lambdaUpdate().eq(NbmhMedicalOrder::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhMedicalOrder> query(NbmhMedicalOrder entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhMedicalOrder> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhMedicalOrder> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhMedicalOrderMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhMedicalOrder> list(NbmhMedicalOrder entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhMedicalOrder> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhMedicalOrderMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhMedicalOrder entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhMedicalOrderMapper.updateById(entity);
            }
        });
    }

}
