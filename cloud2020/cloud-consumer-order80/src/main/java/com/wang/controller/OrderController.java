package com.wang.controller;

import com.wang.entities.CommonResult;
import com.wang.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;

/**
 * @author wzm
 * @since 2022/6/13
 */
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;


    //public static final String PAYMENT_SRV = "http://localhost:8001";

    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_SRV = "http://CLOUD-PAYMENT-SERVICE";

    @RequestMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        /**
         * 请求地址，请求参数，转换对象
         */
        return restTemplate.postForObject(PAYMENT_SRV+"/payment/create"
            ,payment
            ,CommonResult.class);

    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_SRV+"/payment/get/"+id
                ,CommonResult.class
        );
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_SRV+"/payment/get/"+id
                ,CommonResult.class
        );
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(50001,"操作失败");
        }
    }
}
