package com.wang.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author wzzmm
 * @since 2022/7/17 22:07
 */

@Service
public class PaymentServiceImpl implements PaymentService{
    //能正常访问的方法
    @Override
    public String paymentInfoOk(Integer id){
        return "线程池："+Thread.currentThread().getName()+",paymentInfoOk"+id+"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoNotOkHandler",commandProperties = {
            //线程超时时间为3s
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    @Override
    public String paymentInfoNotOk(Integer id) {
//        int i = 10/0;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+",paymentInfoNotOk"+id+"\t"+"耗时3s";
    }

    /**
     * 兜底方法
     * 1.调用超时,运行异常(服务降级)
     * 2.服务熔断
     * 3.服务限流
     */
    public String paymentInfoNotOkHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+",paymentInfoNotOkHandler"+id+"\t"+"o(╥﹏╥)o";
    }
}
