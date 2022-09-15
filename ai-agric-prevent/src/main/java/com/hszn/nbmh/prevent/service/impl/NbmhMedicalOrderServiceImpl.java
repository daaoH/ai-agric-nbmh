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
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;
import com.hszn.nbmh.pay.api.feign.RemoteUnrealPaymentService;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.params.input.NbmhMedicalOrderParam;
import com.hszn.nbmh.prevent.mapper.NbmhMedicalOrderMapper;
import com.hszn.nbmh.prevent.service.INbmhMedicalAcceptService;
import com.hszn.nbmh.prevent.service.INbmhMedicalOrderService;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.input.CoinParam;
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

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteUnrealPaymentService remoteUnrealPaymentService;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhMedicalOrderParam> nbmhMedicalOrderParamList) {

        return nbmhMedicalOrderParamList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setId(new SnowFlakeIdUtil(1, 0).nextId()).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            NbmhMedicalOrder nbmhMedicalOrder = new NbmhMedicalOrder();
            BeanUtils.copyProperties(entity, nbmhMedicalOrder);

            int ret = 0;

            if (entity.getAnimalType() == 0 || entity.getAnimalType() == 1) {
                ret = nbmhMedicalOrderMapper.insert(nbmhMedicalOrder.setIsPayFrontMoney(0).setIsPayMedicalMoney(1));
                decreaseCoin(entity);
                savePaymentRecord(entity);
            }

            if (entity.getAnimalType() == 2) {
                ret = nbmhMedicalOrderMapper.insert(nbmhMedicalOrder.setIsPayFrontMoney(1).setIsPayMedicalMoney(0));
                decreaseCoin(entity);
                savePaymentRecord(entity);
            }

            //TODO: 使用消息队列RabbitMq更新订单支付状态


            return ret;
        }).collect(Collectors.toList());
    }

    /**
     * 生成农牧币支付流水记录以及兽医专家派单记录
     */
    public void savePaymentRecord(NbmhMedicalOrderParam entity) {

        List<NbmhMedicalAccept> medicalAcceptList = entity.getMedicalAcceptList();
        if (CollectionUtils.isEmpty(medicalAcceptList)) {
            throw new ServiceException("未指定接诊专家，无法下单");
        }

        Date createTime = new Date();

        for (NbmhMedicalAccept item : medicalAcceptList) {
            if (item.getMeetingAdminStatus() != 1) {
                continue;
            }

            NbmhUnrealPayment unrealPayment = NbmhUnrealPayment.builder().orderId(entity.getId()).payUserId(entity.getFarmerId()).payChannel(1)
                    .tranType(0).tradeStatus(3).incomeUserId(item.getDoctorId()).payEndTime(createTime).createTime(createTime).status(0).build();

            if (entity.getAnimalType() == 0 || entity.getAnimalType() == 1) {
                unrealPayment.setTotalMoney(entity.getMedicalMoney());
            }

            if (entity.getAnimalType() == 2) {
                unrealPayment.setTotalMoney(entity.getFrontMoney());
            }

            remoteUnrealPaymentService.add(unrealPayment);
        }

        medicalAcceptList.forEach(item -> item.setMedicalOrderNumber(entity.getId()).setOrderStatus(0).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1));

        nbmhMedicalAcceptService.saveBatch(medicalAcceptList);
    }

    /**
     * 扣减养殖户农牧币
     */
    public void decreaseCoin(NbmhMedicalOrderParam entity) {

        if (entity.getMedicalType() == 0) {
            remoteUserService.coinUpdate(CoinParam.builder().userId(entity.getFarmerId()).payType(0).payMoney(entity.getMedicalMoney()).bizId(entity.getId()).bizType("支付视频问诊费用").build());
        }

        if (entity.getMedicalType() == 2) {
            remoteUserService.coinUpdate(CoinParam.builder().userId(entity.getFarmerId()).payType(0).payMoney(entity.getFrontMoney()).bizId(entity.getId()).bizType("支付上门问诊定金费用").build());
        }
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
