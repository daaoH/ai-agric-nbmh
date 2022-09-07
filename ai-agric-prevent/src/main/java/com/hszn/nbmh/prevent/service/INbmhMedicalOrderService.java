package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.params.input.NbmhMedicalOrderParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 诊断下单记录 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
public interface INbmhMedicalOrderService extends IService<NbmhMedicalOrder> {

    /**
     * 保存
     *
     * @param nbmhMedicalOrderParamList 诊断下单记录列表
     * @return the list
     */
    List<Integer> save(List<NbmhMedicalOrderParam> nbmhMedicalOrderParamList);

    /**
     * 更新
     *
     * @param nbmhMedicalOrderList 诊断下单记录列表
     * @return the int
     */
    int update(List<NbmhMedicalOrder> nbmhMedicalOrderList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhMedicalOrder> query(@NotNull NbmhMedicalOrder entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhMedicalOrder> list(@NotNull NbmhMedicalOrder entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
