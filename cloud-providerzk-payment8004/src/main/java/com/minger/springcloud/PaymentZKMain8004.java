package com.minger.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient                              // @EnableDiscoveryClient 注解用于向使用 consul 和 zookeeper 作为注册中心时注册服务
public class PaymentZKMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZKMain8004.class, args);
    }
}
