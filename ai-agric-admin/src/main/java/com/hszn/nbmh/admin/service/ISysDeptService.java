package com.hszn.nbmh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.admin.api.entity.SysDept;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Validated
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 保存
     *
     * @param sysDeptList 部门列表
     * @return the list
     */
    List<Integer> save(List<SysDept> sysDeptList);

    /**
     * 更新
     *
     * @param sysDeptList 部门列表
     * @return the int
     */
    int update(List<SysDept> sysDeptList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<SysDept> query(@NotNull SysDept entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<SysDept> list(@NotNull SysDept entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
