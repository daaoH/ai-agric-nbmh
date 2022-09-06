package com.hszn.nbmh.admin.service.impl;

import com.hszn.nbmh.admin.api.entity.SysRoleMenu;
import com.hszn.nbmh.admin.mapper.SysRoleMenuMapper;
import com.hszn.nbmh.admin.service.ISysRoleMenuService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;



}
