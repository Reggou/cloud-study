package com.wang.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    //=========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "15"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达多少次跳闸
    })//在窗口期10s内，15次请求有60%都是失败的，开启断路器(sleepWindowInMilliseconds,它是开启熔断后重新判断是否正常的时间量)
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
