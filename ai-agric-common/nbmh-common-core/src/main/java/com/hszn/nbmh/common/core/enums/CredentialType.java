package com.hszn.nbmh.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Author：袁德民
 * @Description: 用户账号类型
 * @Date:下午10:57 22/8/20
 * @Modified By:
 */
@Getter
@AllArgsConstructor
public enum CredentialType {

    /**
     * 密码登录
     */
    PASSWORD(1, "密码登录"),

    /**
     * 短信登录
     */
    SMS(2, "短信登录"),

    /**
     * 微信登录
     */
    WECHAT(3, "微信登录");

    private Integer code;

    private String desc;
}
