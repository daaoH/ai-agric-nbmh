package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.user.api.entity.NbmhUserFollowExpert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 关注的专家记录表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
public interface INbmhUserFollowExpertService extends IService<NbmhUserFollowExpert> {

    /**
     * 保存
     *
     * @param nbmhUserFollowExpertList 关注的专家列表
     * @return the list
     */
    List<Integer> save(List<NbmhUserFollowExpert> nbmhUserFollowExpertList);

    /**
     * 更新
     *
     * @param nbmhUserFollowExpertList 关注的专家列表
     * @return the int
     */
    int update(List<NbmhUserFollowExpert> nbmhUserFollowExpertList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhUserFollowExpert> query(@NotNull NbmhUserFollowExpert entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhUserFollowExpert> list(@NotNull NbmhUserFollowExpert entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
