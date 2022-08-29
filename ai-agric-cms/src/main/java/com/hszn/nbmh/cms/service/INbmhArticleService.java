package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
public interface INbmhArticleService extends IService<NbmhArticle> {

    /**
     * 保存
     *
     * @param nbmhArticleList 文章信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhArticle> nbmhArticleList);

    /**
     * 更新
     *
     * @param nbmhArticleList 文章信息列表
     * @return the int
     */
    int update(List<NbmhArticle> nbmhArticleList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhArticle> query(@NotNull NbmhArticle entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhArticle> list(@NotNull NbmhArticle entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
