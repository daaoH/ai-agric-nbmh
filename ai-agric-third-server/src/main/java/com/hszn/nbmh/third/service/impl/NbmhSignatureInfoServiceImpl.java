package com.hszn.nbmh.third.service.impl;

import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.hszn.nbmh.third.mapper.NbmhSignatureInfoMapper;
import com.hszn.nbmh.third.service.INbmhSignatureInfoService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 签名信息表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
@Slf4j
@Service("signatureInfoService")
@Transactional(rollbackFor = Exception.class)
public class NbmhSignatureInfoServiceImpl extends ServiceImpl<NbmhSignatureInfoMapper, NbmhSignatureInfo> implements INbmhSignatureInfoService {


    @Override
    public void updateStatus(String flowId, int status,String url) {
        baseMapper.updateStatus(flowId, status,url);
    }
}
