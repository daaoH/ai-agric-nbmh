package com.hszn.nbmh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.admin.api.entity.SysUserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 保存
     *
     * @param sysUserRoleList 用户角色关系列表
     * @return the list
     */
    List<Integer> save(List<SysUserRole> sysUserRoleList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<SysUserRole> query(@NotNull SysUserRole entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<SysUserRole> list(@NotNull SysUserRole entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param sysRoleList 用户角色关系列表
     */
    void delete(@NotEmpty List<SysUserRole> sysRoleList);
}
