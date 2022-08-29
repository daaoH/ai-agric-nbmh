package com.hszn.nbmh.app;

import com.hszn.nbmh.common.annotation.EnableSwagger;
import com.hszn.nbmh.common.feign.annotation.EnableCustomFeignClients;
import com.hszn.nbmh.common.security.annotation.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwagger
@EnableResourceServer
@EnableCustomFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.hszn.nbmh", "com.hszn.nbmh.app", "com.hszn.nbmh.common"})
public class AiAgricAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricAppApplication.class, args);
    }

}
