package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.hszn.nbmh.user.mapper.SysOauthClientDetailsMapper;
import com.hszn.nbmh.user.service.ISysOauthClientDetailsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 终端信息表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
@Slf4j
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements ISysOauthClientDetailsService {
    @Resource
    private SysOauthClientDetailsMapper sysOauthClientDetailsMapper;


    /**
     * 通过ID删除客户端
     * @param id
     * @return
     */
    @Override
    public Boolean removeClientDetailsById(String id) {
        return this.removeById(id);
    }

    /**
     * 根据客户端信息
     * @param clientDetails
     * @return
     */
    @Override
    public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        return this.updateById(clientDetails);
    }

    /**
     * 清除客户端缓存
     */
    @Override
    public void clearClientCache() {

    }

}
