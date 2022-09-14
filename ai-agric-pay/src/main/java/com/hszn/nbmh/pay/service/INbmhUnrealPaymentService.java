package com.hszn.nbmh.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 虚拟支付信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-09-13
 */
public interface INbmhUnrealPaymentService extends IService<NbmhUnrealPayment> {

    /**
     * 保存
     *
     * @param nbmhUnrealPaymentParamList 虚拟支付信息记录列表
     * @return the list
     */
    List<Integer> save(List<NbmhUnrealPayment> nbmhUnrealPaymentParamList);

    /**
     * 更新
     *
     * @param nbmhUnrealPaymentParamList 虚拟支付信息记录列表
     * @return the int
     */
    int update(List<NbmhUnrealPayment> nbmhUnrealPaymentParamList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhUnrealPayment> query(@NotNull NbmhUnrealPayment entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhUnrealPayment> list(@NotNull NbmhUnrealPayment entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);

}
