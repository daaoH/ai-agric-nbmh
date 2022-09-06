package com.hszn.nbmh.admin.controller;


import cn.hutool.core.lang.tree.Tree;
import com.hszn.nbmh.admin.api.entity.SysMenu;
import com.hszn.nbmh.admin.service.ISysMenuService;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.log.annotation.SysLog;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@RequiredArgsConstructor
@Tag(name = "菜单管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    private final ISysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     * @param parentId 父节点ID
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public Result<List<Tree<Long>>> getUserMenu(Long parentId) {
        // 获取符合条件的菜单
        Set<SysMenu> menuSet = SecurityUtils.getSysRoles().stream().map(sysMenuService::findMenuByRoleId)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        return Result.ok(sysMenuService.filterMenu(menuSet, parentId));
    }

    /**
     * 返回树形菜单集合
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public Result<List<Tree<Long>>> getTree(boolean lazy, Long parentId) {
        return Result.ok(sysMenuService.treeMenu(lazy, parentId));
    }

    /**
     * 返回角色的菜单集合
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public Result<List<Long>> getRoleTree(@PathVariable Long roleId) {
        return Result.ok(
                sysMenuService.findMenuByRoleId(roleId).stream().map(SysMenu::getId).collect(Collectors.toList()));
    }

    /**
     * 通过ID查询菜单的详细信息
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id:\\d+}")
    public Result<SysMenu> getById(@PathVariable Long id) {
        return Result.ok(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     * @param sysMenu 菜单信息
     * @return 含ID 菜单信息
     */
    @SysLog("新增菜单")
    @PostMapping
    public Result<SysMenu> save(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.ok(sysMenu);
    }

    /**
     * 删除菜单
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id:\\d+}")
    public Result<Boolean> removeById(@PathVariable Long id) {
        return Result.ok(sysMenuService.removeMenuById(id));
    }

    /**
     * 更新菜单
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    public Result<Boolean> update(@Valid @RequestBody SysMenu sysMenu) {
        return Result.ok(sysMenuService.updateMenuById(sysMenu));
    }

    /**
     * 清除菜单缓存
     */
    @SysLog("清除菜单缓存")
    @DeleteMapping("/cache")
    public Result clearMenuCache() {
        sysMenuService.clearMenuCache();
        return Result.ok();
    }
}
