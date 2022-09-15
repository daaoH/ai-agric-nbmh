package com.hszn.nbmh.third.service.impl;

import com.hszn.nbmh.third.entity.NbmhSignatureHistory;
import com.hszn.nbmh.third.mapper.NbmhSignatureHistoryMapper;
import com.hszn.nbmh.third.service.INbmhSignatureHistoryService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 签名信息历史表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
@Slf4j
@Service("signatureHistoryService")
@Transactional(rollbackFor = Exception.class)
public class NbmhSignatureHistoryServiceImpl extends ServiceImpl<NbmhSignatureHistoryMapper, NbmhSignatureHistory> implements INbmhSignatureHistoryService {
    @Resource
    private NbmhSignatureHistoryMapper nbmhSignatureHistoryMapper;


}
