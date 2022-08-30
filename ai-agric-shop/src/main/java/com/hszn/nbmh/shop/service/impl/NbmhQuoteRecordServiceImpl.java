package com.hszn.nbmh.shop.service.impl;

import com.hszn.nbmh.shop.api.entity.NbmhQuoteRecord;
import com.hszn.nbmh.shop.mapper.NbmhQuoteRecordMapper;
import com.hszn.nbmh.shop.service.INbmhQuoteRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 报价表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhQuoteRecordServiceImpl extends ServiceImpl<NbmhQuoteRecordMapper, NbmhQuoteRecord> implements INbmhQuoteRecordService {
    @Resource
    private NbmhQuoteRecordMapper nbmhQuoteRecordMapper;



}
