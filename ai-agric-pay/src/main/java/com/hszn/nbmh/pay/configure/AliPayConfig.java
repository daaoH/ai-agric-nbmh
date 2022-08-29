package com.hszn.nbmh.pay.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@PropertySource(value = "alipay-${spring.profiles.active}.properties")
//@PropertySource(value="qxzt-server-wholesale-store-dev.yaml")
//@ConfigurationProperties(prefix="alipay")
//@Component

@Configuration
//@RefreshScope
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayConfig {
    /**
     * 应用私钥
     */
    public String APPPRIVATEKEY;

    /**
     * 应用公钥
     */
    public String APPPUBLICKEY;

    public String APPLYPUBLICKEY;
    /**
     * 支付宝APPID
     */
    public String APPID;

    /**
     * 应用公钥证书路径
     */
    public String APPCERTPATH;

    /**
     * 支付宝公钥证书文件路径
     */
    public String ALIPAYCERTPATH;

    /**
     * 支付宝CA根证书文件路径
     */
    public String ALIPAYROOTCERTPATH;

    /**
     * 请求网关
     * https://openapi.alipay.com/gateway.do
     */
    public String SERVERURL;

    /**
     * 字符集
     */
    public String CHARSET;

    /**
     * 签名类型
     */
    public String SIGNTYPE;

    /**
     * 格式
     */
    public String FORMAT;

    private String type;

    private String notifyUrl;
}
