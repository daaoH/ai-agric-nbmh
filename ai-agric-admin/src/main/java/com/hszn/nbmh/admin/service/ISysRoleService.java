package com.hszn.nbmh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.admin.api.entity.SysRole;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Validated
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 保存
     *
     * @param sysRoleList 系统角色列表
     * @return the list
     */
    List<Integer> save(List<SysRole> sysRoleList);

    /**
     * 更新
     *
     * @param sysRoleList 系统角色列表
     * @return the int
     */
    int update(List<SysRole> sysRoleList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<SysRole> query(@NotNull SysRole entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<SysRole> list(@NotNull SysRole entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
