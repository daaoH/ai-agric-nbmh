package com.hszn.nbmh.third.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 快递鸟接口 配置类
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Data
@Component
@ConfigurationProperties(prefix = "kd")
public class KdSearchProperties {

    /**
     * 商户ID
     */
    private String eBusinessId;

    /**
     * API key
     */
    private String apiKey;

    /**
     * ReqURL
     */
    private String reqUrl;

    /**
     * RequestType
     */
    private String requestType;

}
