package com.hszn.nbmh.third.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 模板路径
 */
@Configuration
@ConfigurationProperties(prefix="template")
@Data
public class TemplatePathConfig {

    //测试模板
    public String testFileTemplate;
}
