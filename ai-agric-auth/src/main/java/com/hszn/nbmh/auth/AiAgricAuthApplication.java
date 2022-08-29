package com.hszn.nbmh.auth;

import com.hszn.nbmh.common.feign.annotation.EnableCustomFeignClients;
import com.hszn.nbmh.common.security.annotation.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableResourceServer
@EnableDiscoveryClient
@EnableCustomFeignClients
@SpringBootApplication(scanBasePackages = {"com.hszn.nbmh", "com.hszn.nbmh.auth", "com.hszn.nbmh.common"})
public class AiAgricAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricAuthApplication.class, args);
    }

}
