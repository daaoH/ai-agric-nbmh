package com.hszn.nbmh.common.core.enums;


import lombok.Getter;
import lombok.Setter;

public enum CommonEnum {

    SUCCESS_RESPONSE(200, "成功"),
    FAILED_RESPONSE(500, "失败"),
    NOT_FOUND(404, "404 没找到请求"),
    AUTH_FAIL(410, "认证失败"),
    AUTH_EXPIRES(411, "认证过期"),
    PARAM_MISS(511, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(512, "请求参数类型错误"),
    PARAM_BIND_ERROR(513, "请求参数绑定错误"),
    PARAM_VALID_ERROR(514, "参数校验失败"),
    MSG_NOT_READABLE(515, "消息不能读取"),
    METHOD_NOT_SUPPORTED(516, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(517, "不支持当前媒体类型"),
    MEDIA_TYPE_NOT_ACCEPT(518, "不接受的媒体类型"),
    REQ_REJECT(520, "请求被拒绝"),

    DATA_NOT_EXIST(521, "数据不存在"),
    DATA_EXISTED(522, "数据已存在"),
    DATA_ADD_FAILED(523, "数据添加失败"),
    DATA_UPDATE_FAILED(524, "数据更新失败"),
    DATA_DELETE_FAILED(525, "数据删除失败"),
    DATA_QUERY_FAILED(526, "数据查询失败"),
    DATA_SUBMIT_FAILED(527, "数据提交失败"),

    FALL_BACK_MSG(555, "服务繁忙，请稍后再试"),

    SMS_VALIDATE_FAIL(600, "短信验证失败，请重试!"),
    SMS_SEND_FAIL(610, "短信验证失败，请重试!"),

    CART_NUM_ERROR(1001, "购买数量必须大于0"),
    STOCK_NUM_ERROR(1002, "库存数量不足"),

    ORDER_PARAM_ERROR(1101, "订单参数错误");


    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private String msg;


    CommonEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
