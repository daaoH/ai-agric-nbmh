package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.mapper.NbmhUserMapper;
import com.hszn.nbmh.user.service.INbmhUserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户基本表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserServiceImpl extends ServiceImpl<NbmhUserMapper, NbmhUser> implements INbmhUserService {

    @Resource
    private NbmhUserMapper nbmhUserMapper;



}
