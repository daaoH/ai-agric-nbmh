package com.hszn.nbmh.admin.service.impl;

import com.hszn.nbmh.admin.api.entity.SysRole;
import com.hszn.nbmh.admin.mapper.SysRoleMapper;
import com.hszn.nbmh.admin.service.ISysRoleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;



}
