package com.minger.springcloud.controller;

import com.minger.springcloud.entities.CommonResult;
import com.minger.springcloud.entities.Payment;
import com.minger.springcloud.loadbalance.LoadBalancer;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

 // 单机版在开发的时候地址是写死的，在集群环境下这个地址是不能访问的
 // public static final String SERVICE_URL = "http://localhost:8001";

 // 配置集群的时候需要用在 eureka 中的注册服务名作为访问地址，另外需要在 ApplicationContextConfig 里添加 @LoadBalanced 注解开启负载均衡功能。
    public static final String SERVICE_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("******插入成功："+ payment);
        return restTemplate.postForObject(SERVICE_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("******查询成功： "+restTemplate);
        return restTemplate.getForObject(SERVICE_URL + "/payment/get/" +id,CommonResult.class);
    }

    // getForEntity 方法重写
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(SERVICE_URL + "/payment/get/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return  entity.getBody();
        }else {
            return new CommonResult<>( 400, "操作失败！");
        }
    }

//   @GetMapping(value = "/consumer/payment/lb")
//    public String getPaymentLB()
//    {
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//
//        if(instances == null || instances.size() <= 0)
//        {
//            return null;
//        }
//
//        ServiceInstance serviceInstance = loadBalancer.instances(instances);
//        URI uri = serviceInstance.getUri();
//
//        return restTemplate.getForObject(uri+"/payment/lb",String.class);
//
//    }



}
