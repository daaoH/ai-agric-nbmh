package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.mapper.NbmhUserExtraInfoMapper;
import com.hszn.nbmh.user.service.INbmhUserExtraInfoService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户信息扩展表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserExtraInfoServiceImpl extends ServiceImpl<NbmhUserExtraInfoMapper, NbmhUserExtraInfo> implements INbmhUserExtraInfoService {
    @Resource
    private NbmhUserExtraInfoMapper nbmhUserExtraInfoMapper;



}
