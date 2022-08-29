package com.hszn.nbmh.pay.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;

//@Configuration
//@PropertySource(value = "alipay-${spring.profiles.active}.properties")
//@PropertySource(value="qxzt-server-wholesale-store-dev.yaml")
//@ConfigurationProperties(prefix="wxpay")
//@Component


@Configuration
//@RefreshScope
@Data
@ConfigurationProperties(prefix = "wxpay")
public class WxPayConfig {
    /**
     * appId
     */
    public String APPID;

    /**
     * 密钥 mchId
     */
    public String MCHID;

    /**
     * 回调 notifyUrl
     */
    public String NOTIFYURL;

    /**
     * 回调 key
     */
    public String KEY;

    /**
     * 下单 API 地址sandboxnew  https://api.mch.weixin.qq.com/pay/unifiedorder
     * placeUrl
     */
    public String PLACEURL;
}
