package com.hszn.nbmh.third.configure;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss配置类
 *
 * @author wangjun
 */
@Configuration
public class OSSClientConfig {

    @Value("${oss.endpoint}")
    String endpoint;
    @Value("${oss.keyid}")
    String accessKeyId;
    @Value("${oss.keysecret}")
    String accessKeySecret;

    // 创建OSS客户端Bean
    @Bean
    public OSSClient getOSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
