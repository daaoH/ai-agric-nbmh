package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.prevent.api.entity.NbmhButcherReport;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 屠宰/无害化申报信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
public interface INbmhButcherReportService extends IService<NbmhButcherReport> {

    /**
     * 保存
     *
     * @param butcherReportList 屠宰申报信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhButcherReport> butcherReportList);

    /**
     * 更新
     *
     * @param nbmhButcherReportList 屠宰申报信息列表
     * @return the int
     */
    int update(List<NbmhButcherReport> nbmhButcherReportList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhButcherReport> query(@NotNull NbmhButcherReport entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhButcherReport> list(@NotNull NbmhButcherReport entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
