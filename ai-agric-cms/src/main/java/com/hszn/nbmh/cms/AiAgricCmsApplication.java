package com.hszn.nbmh.cms;

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
@SpringBootApplication(scanBasePackages={"com.hszn.nbmh.cms"})
@MapperScan(basePackages={"com.hszn.nbmh.cms.mapper"})
public class AiAgricCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricCmsApplication.class, args);
    }

}
