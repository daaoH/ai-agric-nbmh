package com.hszn.nbmh.third.configure;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 百度审核配置类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 10:48
 */
@Configuration
public class AipContentCensorConfig {

    @Value("${contentCensor.app_id}")
    String APP_ID = "你的 App ID";
    @Value("${contentCensor.api_key}")
    String API_KEY = "你的 Api Key";
    @Value("${contentCensor.secret_key}")
    String SECRET_KEY = "你的 Secret Key";

    @Bean
    public AipContentCensor contentCensor() {
        AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
