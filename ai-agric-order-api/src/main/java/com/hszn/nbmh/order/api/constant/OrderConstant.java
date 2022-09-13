package com.hszn.nbmh.order.api.constant;

/**
 * 订单常用变量
 */
public interface OrderConstant {

    /**
     * 订单模块默认交换机
     */
    String EXCHANGE_NAME = "nbmh.order.direct";
    /**
     * 订单延迟队列交换机
     */
    String DELAY_EXCHANGE_NAME = "nbmh.order.delay.direct";
    /**
     * 物流模块默认交换机
     */
    String DELIVER_EXCHANGE_NAME = "nbmh.deliver.direct";
    /**
     * 支付模块默认交换机
     */
    String PAYMENT_EXCHANGE = "payment.notify.exchange";
    /**
     * 账户模块默认交换机
     */
    String ACCOUNT_EXCHANGE = "nbmh.account.exchange";

}
