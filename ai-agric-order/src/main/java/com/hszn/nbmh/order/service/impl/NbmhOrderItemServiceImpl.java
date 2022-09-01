package com.hszn.nbmh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.hszn.nbmh.order.mapper.NbmhOrderItemMapper;
import com.hszn.nbmh.order.service.INbmhOrderItemService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhOrderItemServiceImpl extends ServiceImpl<NbmhOrderItemMapper, NbmhOrderItem> implements INbmhOrderItemService {

    @Override
    public List<NbmhOrderItem> findByOrderId(Long orderId) {
        LambdaQueryWrapper<NbmhOrderItem> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NbmhOrderItem::getOrderId, orderId);
        return list(wrapper);
    }

    @Override
    public List<NbmhOrderItem> findByName(String name, String userId) {
        return baseMapper.findByNmAndUid(name, userId);
    }
}
