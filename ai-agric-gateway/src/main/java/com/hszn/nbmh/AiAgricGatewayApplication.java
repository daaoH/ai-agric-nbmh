package com.hszn.nbmh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
public class AiAgricGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAgricGatewayApplication.class, args);
    }

}
