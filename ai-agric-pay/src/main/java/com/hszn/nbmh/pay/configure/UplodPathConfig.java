package com.hszn.nbmh.pay.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "uplodpath")
@Data
/**
 * 模板路径
 */
public class UplodPathConfig {

    //上传路径
    public String path;
}
