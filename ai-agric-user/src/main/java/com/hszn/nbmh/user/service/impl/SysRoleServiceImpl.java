package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.SysRole;
import com.hszn.nbmh.user.mapper.SysRoleMapper;
import com.hszn.nbmh.user.service.ISysRoleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;



}
