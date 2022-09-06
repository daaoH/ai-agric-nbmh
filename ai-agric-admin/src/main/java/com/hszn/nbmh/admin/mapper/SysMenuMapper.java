package com.hszn.nbmh.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hszn.nbmh.admin.api.entity.SysMenu;

import java.util.Set;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色编号查询菜单
     * @param roleId 角色ID
     * @return
     */
    Set<SysMenu> listMenusByRoleId(Long roleId);
}
