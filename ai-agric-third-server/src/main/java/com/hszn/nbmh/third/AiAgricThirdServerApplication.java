package com.hszn.nbmh.third;

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
@MapperScan("com.hszn.nbmh.*.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.hszn.nbmh"})
public class AiAgricThirdServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricThirdServerApplication.class, args);
    }

}
