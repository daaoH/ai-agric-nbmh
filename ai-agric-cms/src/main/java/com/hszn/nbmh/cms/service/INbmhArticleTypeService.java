package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 文章类型表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
public interface INbmhArticleTypeService extends IService<NbmhArticleType> {

    /**
     * 保存
     *
     * @param nbmhArticleTypeList 文章类型列表
     * @return the list
     */
    List<Integer> save(List<NbmhArticleType> nbmhArticleTypeList);

    /**
     * 更新
     *
     * @param nbmhArticleTypeList 文章类型列表
     * @return the int
     */
    int update(List<NbmhArticleType> nbmhArticleTypeList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhArticleType> query(@NotNull NbmhArticleType entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhArticleType> list(@NotNull NbmhArticleType entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
