package com.hszn.nbmh.user;

import com.hszn.nbmh.common.annotation.EnableSwagger;
import com.hszn.nbmh.common.feign.annotation.EnableCustomFeignClients;
import com.hszn.nbmh.common.security.annotation.EnableResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableSwagger
@EnableResourceServer
@EnableCustomFeignClients
@MapperScan("com.hszn.nbmh.*.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.hszn.nbmh", "com.hszn.nbmh.user", "com.hszn.nbmh.common"})
public class AiAgricUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricUserApplication.class, args);
    }

}
