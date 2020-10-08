package com.minger.springcloud.loadbalance;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance instances(List<InstanceInfo> serviceInstances);

}
