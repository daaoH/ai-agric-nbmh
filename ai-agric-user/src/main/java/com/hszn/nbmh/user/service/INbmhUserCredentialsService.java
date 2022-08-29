package com.hszn.nbmh.user.service;

import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserCredentials;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户凭证表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-08-18
 */
public interface INbmhUserCredentialsService extends IService<NbmhUserCredentials> {


    NbmhUserCredentials queryByUsername(String username);
}
