package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserCredentials;
import com.hszn.nbmh.user.mapper.NbmhUserCredentialsMapper;
import com.hszn.nbmh.user.service.INbmhUserCredentialsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户凭证表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-18
 */
@Slf4j
@Service
public class NbmhUserCredentialsServiceImpl extends ServiceImpl<NbmhUserCredentialsMapper, NbmhUserCredentials> implements INbmhUserCredentialsService {

    @Resource
    private NbmhUserCredentialsMapper userCredentialsMapper;

    @Override
    public NbmhUserCredentials queryByUsername(String userName) {
        return userCredentialsMapper.selectOne(Wrappers.<NbmhUserCredentials>query().lambda().eq(NbmhUserCredentials::getUserName, userName));
    }
}
