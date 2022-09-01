package com.hszn.nbmh.order.service;

import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
public interface INbmhOrderItemService extends IService<NbmhOrderItem> {

    /**
     * 根据订单查询订单明细
     *
     * @param orderId
     * @return
     */
    List<NbmhOrderItem> findByOrderId(Long orderId);

    /**
     * 根据订单商品搜索订单
     *
     * @param name
     * @param userId
     * @return
     */
    List<NbmhOrderItem> findByName(String name, String userId);
}
