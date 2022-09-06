package com.hszn.nbmh.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.admin.api.entity.SysMenu;
import com.hszn.nbmh.admin.api.entity.SysRoleMenu;
import com.hszn.nbmh.admin.api.enums.MenuTypeEnum;
import com.hszn.nbmh.admin.mapper.SysMenuMapper;
import com.hszn.nbmh.admin.mapper.SysRoleMenuMapper;
import com.hszn.nbmh.admin.service.ISysMenuService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.constant.CommonConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;


    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Set<SysMenu> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功, false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMenuById(Long id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        // 删除当前菜单及其子菜单
        return this.removeById(id);
    }

    @Override
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<Tree<Long>> treeMenu(boolean lazy, Long parentId) {
        if (!lazy) {
            List<TreeNode<Long>> collect = baseMapper
                    .selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSortOrder)).stream()
                    .map(getNodeFunction()).collect(Collectors.toList());

            return TreeUtil.build(collect, CommonConstant.MENU_TREE_ROOT_ID);
        }

        Long parent = parentId == null ? CommonConstant.MENU_TREE_ROOT_ID : parentId;

        List<TreeNode<Long>> collect = baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent)
                        .orderByAsc(SysMenu::getSortOrder))
                .stream().map(getNodeFunction()).collect(Collectors.toList());

        return TreeUtil.build(collect, parent);
    }

    /**
     * 查询菜单
     * @param all 全部菜单
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> all, Long parentId) {
        List<TreeNode<Long>> collect = all.stream()
                .filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
                .filter(menu -> StrUtil.isNotBlank(menu.getPath())).map(getNodeFunction()).collect(Collectors.toList());
        Long parent = parentId == null ? CommonConstant.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(collect, parent);
    }

    @Override
    public void clearMenuCache() {

    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSortOrder());
            // 扩展属性
            Map<String, Object> extra = new HashMap<>();
            extra.put("icon", menu.getIcon());
            extra.put("path", menu.getPath());
            extra.put("type", menu.getType());
            extra.put("permission", menu.getPermission());
            extra.put("label", menu.getName());
            extra.put("sortOrder", menu.getSortOrder());
            extra.put("keepAlive", menu.getKeepAlive());
            node.setExtra(extra);
            return node;
        };
    }
}
