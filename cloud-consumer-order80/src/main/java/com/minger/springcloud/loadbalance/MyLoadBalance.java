package com.minger.springcloud.loadbalance;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalance implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current, next));

        System.out.println("******第几次访问,next数字: " + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<InstanceInfo> serviceInstances)
    {
        int index = getAndIncrement() % serviceInstances.size();

        return (ServiceInstance) serviceInstances.get(index);
    }

}
