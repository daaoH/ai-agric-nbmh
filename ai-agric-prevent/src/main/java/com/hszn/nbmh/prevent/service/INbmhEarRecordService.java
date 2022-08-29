package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.params.input.NbmhEarRecordParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 耳标补打信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
public interface INbmhEarRecordService extends IService<NbmhEarRecord> {

    /**
     * 保存
     *
     * @param nbmhEarRecordList 耳标补打信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhEarRecord> nbmhEarRecordList);

    /**
     * 更新
     *
     * @param nbmhEarRecordList 耳标补打信息列表
     * @return the int
     */
    int update(List<NbmhEarRecord> nbmhEarRecordList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhEarRecord> query(@NotNull NbmhEarRecordParam entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhEarRecord> list(@NotNull NbmhEarRecord entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
