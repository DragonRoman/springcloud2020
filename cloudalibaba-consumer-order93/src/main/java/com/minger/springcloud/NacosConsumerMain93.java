package com.minger.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain93 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain93.class,args);
    }
}
