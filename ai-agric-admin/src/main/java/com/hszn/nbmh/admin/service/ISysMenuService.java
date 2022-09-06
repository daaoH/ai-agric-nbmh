package com.hszn.nbmh.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.hszn.nbmh.admin.api.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    Set<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功, false失败
     */
    Boolean removeMenuById(Long id);

    /**
     * 更新菜单信息
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    Boolean updateMenuById(SysMenu sysMenu);

    /**
     * 构建树
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    List<Tree<Long>> treeMenu(boolean lazy, Long parentId);

    /**
     * 查询菜单
     * @param menuSet
     * @param parentId
     * @return
     */
    List<Tree<Long>> filterMenu(Set<SysMenu> menuSet, Long parentId);

    /**
     * 清除菜单缓存
     */
    void clearMenuCache();
}
