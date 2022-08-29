package com.hszn.nbmh.third.enums;

import lombok.Getter;
import lombok.Setter;

public enum ALiyunEnum {

    SUCCESS_RESPONSE(200, "成功"),
    FAILED_RESPONSE(500, "失败"),
    NOT_FOUND(404, "404 没找到请求"),
    CERTIFY_FAIL(420, "certifyId获取失败"),
    AUTH_FAIL(424, "身份认证记录不存在");


    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private String msg;


    ALiyunEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
