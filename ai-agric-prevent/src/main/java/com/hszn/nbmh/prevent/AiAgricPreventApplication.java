package com.hszn.nbmh.prevent;

import com.hszn.nbmh.common.annotation.EnableSwagger;
import com.hszn.nbmh.common.feign.annotation.EnableCustomFeignClients;
import com.hszn.nbmh.common.security.annotation.EnableResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwagger
@EnableResourceServer
@EnableCustomFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.hszn.nbmh", "com.hszn.nbmh.prevent", "com.hszn.nbmh.common"})
@MapperScan(basePackages={"com.hszn.nbmh.prevent.mapper"})
public class AiAgricPreventApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiAgricPreventApplication.class, args);
    }

}
