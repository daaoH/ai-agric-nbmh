package com.hszn.nbmh.third.config;

import lombok.Data;

/**
 * <p>
 * 阿里云--短信服务 配置类
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
@Data
public class SmsProperties {

    /**
     *短信签名
     */
    private String signName;

    /**
     *模板CODE
     */
    private String templateCode;

    /**
     *验证码有效时间
     */
    private Long timeout;

}
