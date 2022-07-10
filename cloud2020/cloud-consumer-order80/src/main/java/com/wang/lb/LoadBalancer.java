package com.wang.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wzm
 * @since 2022/7/10
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
