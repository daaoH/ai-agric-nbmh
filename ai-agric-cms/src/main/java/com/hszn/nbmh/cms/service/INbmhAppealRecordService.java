package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAppealRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 申诉记录表 服务类
 * </p>
 *
 * @author pyq
 * @since 2022-09-01
 */
public interface INbmhAppealRecordService extends IService<NbmhAppealRecord> {

 /**
 * 保存
 *
 * @param nbmhAppealRecordList 申诉记录列表
 * @return the list
 */
List<Integer> save(List<NbmhAppealRecord> nbmhAppealRecordList);

    /**
     * 更新
     *
     * @param nbmhAppealRecordList 申诉记录列表
     * @return the int
     */
    int update(List<NbmhAppealRecord> nbmhAppealRecordList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhAppealRecord> query(@NotNull NbmhAppealRecord entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhAppealRecord> list(@NotNull NbmhAppealRecord entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);
}