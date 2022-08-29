package com.hszn.nbmh.third.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 阿里云接口 配置类
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class ALiYunProperties {

    /**
     * 阿里云应用Key
     */
    private String accessKey;

    /**
     * 阿里云应用Secret
     */
    private String secret;

    /**
     * 阿里云身份认证相关配置
     */
    @NestedConfigurationProperty
    private CloudAuthProperties cloudAuth;

    /**
     * 阿里云短信发送相关配置
     */
    @NestedConfigurationProperty
    private SmsProperties sms;

}
