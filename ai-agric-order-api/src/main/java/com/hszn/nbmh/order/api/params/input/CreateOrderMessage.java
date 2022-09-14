package com.hszn.nbmh.order.api.params.input;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description: 创建订单消息体
 * @Date:下午8:16 22/9/13
 * @Modified By:
 */
@Data
public class CreateOrderMessage implements Serializable {

    private Long orderId;

    private CreateOrderParam param;
}
