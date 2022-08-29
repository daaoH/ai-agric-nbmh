package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.cms.api.entity.NbmhAd;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 广告表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
public interface INbmhAdService extends IService<NbmhAd> {

    /**
     * 保存
     *
     * @param nbmhAdList 广告信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhAd> nbmhAdList);

    /**
     * 更新
     *
     * @param nbmhAdList 广告信息列表
     * @return the int
     */
    int update(List<NbmhAd> nbmhAdList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhAd> query(@NotNull NbmhAd entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhAd> list(@NotNull NbmhAd entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
