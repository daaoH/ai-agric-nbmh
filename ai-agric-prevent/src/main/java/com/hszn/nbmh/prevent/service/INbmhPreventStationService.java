package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 防疫站信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
public interface INbmhPreventStationService extends IService<NbmhPreventStation> {

    /**
     * 保存
     *
     * @param nbmhPreventStationList 防疫站信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhPreventStation> nbmhPreventStationList);

    /**
     * 更新
     *
     * @param nbmhPreventStationList 防疫站信息列表
     * @return the int
     */
    int update(List<NbmhPreventStation> nbmhPreventStationList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhPreventStation> query(@NotNull NbmhPreventStation entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhPreventStation> list(@NotNull NbmhPreventStation entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
