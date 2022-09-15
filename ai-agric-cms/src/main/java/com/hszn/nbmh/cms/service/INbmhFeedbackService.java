package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhFeedback;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 意见反馈表 服务类
 * </p>
 *
 * @author pyq
 * @since 2022-09-03
 */
public interface INbmhFeedbackService extends IService<NbmhFeedback> {
    /**
     * 保存
     *
     * @param nbmhFeedbackList 意见反馈列表
     * @return the list
     */
    List<Integer> save(List<NbmhFeedback> nbmhFeedbackList);

    /**
     * 更新
     *
     * @param nbmhFeedbackList 意见反馈列表
     * @return the int
     */
    int update(List<NbmhFeedback> nbmhFeedbackList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhFeedback> query(@NotNull NbmhFeedback entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhFeedback> list(@NotNull NbmhFeedback entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
