package com.hszn.nbmh.order.service.impl;

import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import com.hszn.nbmh.order.mapper.NbmhReceiptMapper;
import com.hszn.nbmh.order.service.INbmhReceiptService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 发票信息 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhReceiptServiceImpl extends ServiceImpl<NbmhReceiptMapper, NbmhReceipt> implements INbmhReceiptService {
    @Resource
    private NbmhReceiptMapper nbmhReceiptMapper;



}
