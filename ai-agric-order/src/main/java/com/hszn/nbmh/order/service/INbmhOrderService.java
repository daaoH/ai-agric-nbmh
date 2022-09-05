package com.hszn.nbmh.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.api.params.input.CreateOrderParam;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
public interface INbmhOrderService extends IService<NbmhOrder> {

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    Page<NbmhOrder> search(QueryRequest<OrderSearchParam> param);

    /**
     * 生成订单
     * @param order
     * @return
     */
    String createOrder(CreateOrderParam order);
}
