package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 视频课堂 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Validated
public interface INbmhVideoLessonService extends IService<NbmhVideoLesson> {
    /**
     * 保存
     *
     * @param nbmhVideoLessonList 视频课堂列表
     * @return the list
     */
    List<Integer> save(List<NbmhVideoLesson> nbmhVideoLessonList);

    /**
     * 更新
     *
     * @param nbmhVideoLessonList 视频课堂列表
     * @return the int
     */
    int update(List<NbmhVideoLesson> nbmhVideoLessonList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhVideoLesson> query(@NotNull NbmhVideoLesson entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhVideoLesson> list(@NotNull NbmhVideoLesson entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);

    /**
     * 审核
     *
     * @param nbmhVideoLessonList 视频课堂列表
     * @return the int
     */
    int audit(List<NbmhVideoLesson> nbmhVideoLessonList);
}
