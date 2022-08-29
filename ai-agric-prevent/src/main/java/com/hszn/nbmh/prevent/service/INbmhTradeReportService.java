package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 活体交易信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
public interface INbmhTradeReportService extends IService<NbmhTradeReport> {

    /**
     * 保存
     *
     * @param nbmhTradeReportList 活体交易报备信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhTradeReport> nbmhTradeReportList);

    /**
     * 更新
     *
     * @param nbmhTradeReportList 活体交易报备信息列表
     * @return the int
     */
    int update(List<NbmhTradeReport> nbmhTradeReportList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhTradeReport> query(@NotNull NbmhTradeReport entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhTradeReport> list(@NotNull NbmhTradeReport entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
