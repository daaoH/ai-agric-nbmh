package com.hszn.nbmh.order.service.impl;

import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.mapper.NbmhOrderMapper;
import com.hszn.nbmh.order.service.INbmhOrderService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhOrderServiceImpl extends ServiceImpl<NbmhOrderMapper, NbmhOrder> implements INbmhOrderService {
    @Resource
    private NbmhOrderMapper nbmhOrderMapper;



}
