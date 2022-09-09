package com.hszn.nbmh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.admin.api.entity.SysPost;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Validated
public interface ISysPostService extends IService<SysPost> {

    /**
     * 保存
     *
     * @param sysPostList 岗位信息列表
     * @return the list
     */
    List<Integer> save(List<SysPost> sysPostList);

    /**
     * 更新
     *
     * @param sysPostList 岗位信息列表
     * @return the int
     */
    int update(List<SysPost> sysPostList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<SysPost> query(@NotNull SysPost entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<SysPost> list(@NotNull SysPost entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
