package com.hszn.nbmh.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.hszn.nbmh.admin.api.entity.SysMenu;
import com.hszn.nbmh.admin.api.entity.SysPost;
import com.hszn.nbmh.admin.api.entity.SysRole;
import com.hszn.nbmh.admin.api.entity.SysUser;
import com.hszn.nbmh.admin.api.enums.MenuTypeEnum;
import com.hszn.nbmh.admin.api.params.vo.SysLoginUser;
import com.hszn.nbmh.admin.mapper.SysPostMapper;
import com.hszn.nbmh.admin.mapper.SysRoleMapper;
import com.hszn.nbmh.admin.mapper.SysUserMapper;
import com.hszn.nbmh.admin.service.ISysMenuService;
import com.hszn.nbmh.admin.service.ISysUserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPostMapper sysPostMapper;

    @Autowired
    private ISysMenuService sysMenuService;


    @Override
    public SysLoginUser getUserInfo(SysUser sysUser) {
        SysLoginUser userInfo = new SysLoginUser();
        userInfo.setSysUser(sysUser);
        // 设置角色列表
        List<SysRole> roleList = sysRoleMapper.listRolesByUserId(sysUser.getId());
        userInfo.setRoleList(roleList);
        // 设置角色列表 （ID）
        List<Long> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));
        // 设置岗位列表
        List<SysPost> postList = sysPostMapper.listPostsByUserId(sysUser.getId());
        userInfo.setPostList(postList);
        // 设置权限列表（menu.permission）
        Set<String> permissions = roleIds.stream().map(sysMenuService::findMenuByRoleId).flatMap(Collection::stream)
                .filter(m -> MenuTypeEnum.BUTTON.getType().equals(m.getType())).map(SysMenu::getPermission)
                .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));

        return userInfo;
    }
}
