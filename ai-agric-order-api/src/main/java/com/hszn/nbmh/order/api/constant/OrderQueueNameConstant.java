package com.hszn.nbmh.order.api.constant;

/**
 * 订单消息队列名称
 */
public interface OrderQueueNameConstant {

    /**
     * 生成订单
     */
    String ORDER_CREATE = "nbmh.order.create";
    /**
     * 生成订单失败
     */
    String ORDER_CREATE_FAIL = "nbmh.order.create.fail";
    /**
     * 订单已支付
     */
    String ORDER_PAYED = "nbmh.order.payed";

    /**
     * 完成订单
     */
    String ORDER_COMPLETED = "nbmh.order.completed";

    /**
     * 取消订单
     */
    String ORDER_AUTO_CANCEL = "nbmh.order.auto.cancel";
    /**
     * 关闭订单
     */
    String ORDER_CLOSE = "nbmh.order.close";
    /**
     * 订单退款
     */
    String ORDER_RETURN = "nbmh.order.return";
    /**
     * 取消订单失败
     */
    String ORDER_CANCEL_FAIL = "nbmh.order.cancel.fail";
    /**
     * 支付回调
     */
    String PAYMENT_NOTIFY = "nbmh.order.payment.notify";
    /**
     * 退款回调
     */
    String REFUND_NOTIFY = "nbmh.order.refund.notify";
    /**
     * 退款回调成功
     */
    String REFUND_NOTIFY_SUCCEED = "nbmh.order.refund.notify.succeed";

}
