package com.hszn.nbmh.order.api.constant;

import lombok.Getter;

/**
 * 消息队列枚举配置
 *
 */
@Getter
public enum OrderQueueEnum {
    /**
     * 创建订单
     */
    QUEUE_ORDER_CREATE(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_CREATE, "nbmh.order.create"),
    /**
     * 创建订单失败
     */
    QUEUE_ORDER_CREATE_FAIL(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_CREATE_FAIL,
            "nbmh.order.create.fail"),
    /**
     * 订单已支付
     */
    QUEUE_ORDER_PAYED(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_PAYED, "nbmh.order.payed"),

    /**
     * 完成订单
     */
    QUEUE_ORDER_COMPLETED(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_COMPLETED, "nbmh.order.completed"),
    /**
     * 取消订单
     */
    QUEUE_ORDER_AUTO_CANCEL(OrderConstant.DELAY_EXCHANGE_NAME, OrderQueueNameConstant.ORDER_AUTO_CANCEL,
            "nbmh.order.auto.cancel"),
    /**
     * 关闭订单
     */
    QUEUE_ORDER_CLOSE(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_CLOSE, "nbmh.order.close"),
    /**
     * 订单退款
     */
    QUEUE_ORDER_RETURN(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.ORDER_RETURN, "nbmh.order.return"),
    /**
     * 订单退款成功
     */
    QUEUE_ORDER_RETURN_SUCCEED(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.REFUND_NOTIFY_SUCCEED,
            "nbmh.order.refund.notify.succeed"),

    /**
     * 取消订单失败
     */
    QUEUE_ORDER_CANCEL_FAIL(OrderConstant.DELAY_EXCHANGE_NAME, OrderQueueNameConstant.ORDER_CANCEL_FAIL,
            "nbmh.order.cancel.fail"),

    //================================支付模块==========================================================================
    /**
     * 订单支付成功回调
     */
    QUEUE_ORDER_PAYMENT_NOTIFY(OrderConstant.PAYMENT_EXCHANGE, OrderQueueNameConstant.PAYMENT_NOTIFY, "nbmh.order" +
            ".payment.notify"),
    /**
     * 订单退款成功回调
     */
    QUEUE_ORDER_REFUND_NOTIFY(OrderConstant.EXCHANGE_NAME, OrderQueueNameConstant.REFUND_NOTIFY,
            "nbmh.order.refund.notify");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    OrderQueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

}
