package com.wang.controller;

import com.wang.entities.CommonResult;
import com.wang.entities.Payment;
import com.wang.service.PaymentFeignService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign是一个声明式的Web服务客户端，让编写Web服务客户端变得非常容易，只需创建一个接口并在接口上添加注解即可
 *
 * 通过Feign的注解调用service层的接口->
 * 接口通过查找服务注册中心上注册的名字的微服务(@FeignClient(value = "CLOUD-PAYMENT-SERVICE"))
 * 上暴露的接口(@GetMapping(value = "/payment/get/{id}"))进行调用
 *
 * @author wzzmm
 * @since 2022/7/14 1:00
 */

@RestController
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    };
}
