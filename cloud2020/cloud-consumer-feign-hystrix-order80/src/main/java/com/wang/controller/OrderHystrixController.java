package com.wang.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wang.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzzmm
 * @since 2022/7/17 23:39
 */
@RestController
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })//指定的服务降级
    @HystrixCommand//使用全局的服务降级
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfoNotOk(@PathVariable("id") Integer id) {
        int i = 10/0;
        String result = paymentHystrixService.paymentInfoNotOk(id);
        return result;
    }

    /**
     * 订单侧服务降级
     * @param id
     * @return
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //全局服务降级 global fallback
    public String paymentGlobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试";
    }
}
