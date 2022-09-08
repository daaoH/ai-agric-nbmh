package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhRecharge;
import com.hszn.nbmh.user.mapper.NbmhRechargeMapper;
import com.hszn.nbmh.user.service.INbmhRechargeService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhRechargeServiceImpl extends ServiceImpl<NbmhRechargeMapper, NbmhRecharge> implements INbmhRechargeService {
    @Resource
    private NbmhRechargeMapper nbmhRechargeMapper;



}
