package com.ycl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 网关服务
 * @date: 2021/7/28 18:19
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

}
