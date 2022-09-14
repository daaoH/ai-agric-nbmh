package com.hszn.nbmh.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.api.params.input.CreateOrderParam;
import com.hszn.nbmh.order.api.params.input.SettlementParam;
import com.hszn.nbmh.order.api.params.out.SettlementReturn;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;

import java.util.List;

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
     * 检查订单
     * @param order
     * @return
     */
    String preCheckOrder(CreateOrderParam order);

    /**
     * 生成订单
     * @param orderParam
     * @return
     */
    Boolean createOrder(CreateOrderParam orderParam, NbmhUserAddress address, Long orderId);

    /**
     * 取消订单
     * @param orderId
     */
    void cancelOrder(Long orderId);

    /**
     * 支付超时取消订单
     * @param order
     */
    void autoCancelOrder(Long order);

    /**
     * 购物车结算
     * @param param
     * @return
     */
    List<SettlementReturn> settlement(SettlementParam param);
}
