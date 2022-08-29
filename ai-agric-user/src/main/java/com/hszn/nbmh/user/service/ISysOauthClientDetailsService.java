package com.hszn.nbmh.user.service;

import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 终端信息表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
public interface ISysOauthClientDetailsService extends IService<SysOauthClientDetails> {

    /**
     * 通过ID删除客户端
     * @param id
     * @return
     */
    Boolean removeClientDetailsById(String id);

    /**
     * 修改客户端信息
     * @param sysOauthClientDetails
     * @return
     */
    Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);

    /**
     * 清除客户端缓存
     */
    void clearClientCache();
}
