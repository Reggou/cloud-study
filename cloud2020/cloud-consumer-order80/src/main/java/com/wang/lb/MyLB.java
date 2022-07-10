package com.wang.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询算法
 *
 * @author wzm
 * @since 2022/7/10
 */
@Component
@Slf4j
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            //每次重启从1开始计数
            //compareAndSet()如果得到的是期望值则返回true(保证线程安全,别的用户占用线程则false)
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("访问次数next->>>"+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //访问次数 % 有效服务数量
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
