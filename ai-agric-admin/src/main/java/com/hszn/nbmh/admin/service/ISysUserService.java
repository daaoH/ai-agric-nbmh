package com.hszn.nbmh.admin.service;

import com.hszn.nbmh.admin.api.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.admin.api.params.vo.SysLoginUser;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 查询用户信息
     * @param sysUser 用户
     * @return SysLoginUser
     */
    SysLoginUser getUserInfo(SysUser sysUser);
}
