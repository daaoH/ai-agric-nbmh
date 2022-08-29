package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import com.hszn.nbmh.user.mapper.NbmhUserAddressMapper;
import com.hszn.nbmh.user.service.INbmhUserAddressService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserAddressServiceImpl extends ServiceImpl<NbmhUserAddressMapper, NbmhUserAddress> implements INbmhUserAddressService {
    @Resource
    private NbmhUserAddressMapper nbmhUserAddressMapper;



}
