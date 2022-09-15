package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAd;
import com.hszn.nbmh.cms.api.entity.NbmhIssue;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 常见问题表 服务类
 * </p>
 *
 * @author pyq
 * @since 2022-09-05
 */
public interface INbmhIssueService extends IService<NbmhIssue> {
    /**
     * 保存
     *
     * @param nbmhIssueList 常见问题列表
     * @return the list
     */
    List<Integer> save(List<NbmhIssue> nbmhIssueList);

    /**
     * 更新
     *
     * @param nbmhIssueList 常见问题列表
     * @return the int
     */
    int update(List<NbmhIssue> nbmhIssueList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhIssue> query(@NotNull NbmhIssue entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhIssue> list(@NotNull NbmhIssue entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
