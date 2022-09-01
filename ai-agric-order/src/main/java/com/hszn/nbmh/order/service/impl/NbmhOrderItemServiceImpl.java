package com.hszn.nbmh.order.service.impl;

import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.hszn.nbmh.order.mapper.NbmhOrderItemMapper;
import com.hszn.nbmh.order.service.INbmhOrderItemService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

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
    @Resource
    private NbmhOrderItemMapper nbmhOrderItemMapper;



}
