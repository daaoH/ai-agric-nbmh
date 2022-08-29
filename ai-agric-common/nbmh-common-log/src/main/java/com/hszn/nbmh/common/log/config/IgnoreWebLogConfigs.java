package com.hszn.nbmh.common.log.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "web-log")
@Data
public class IgnoreWebLogConfigs {

    /**
     * 要忽略的路径,模糊匹配
     *
     */
    private Map<String, List<String>> ignores = new HashMap<>();

}
