package com.wang.controller;

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
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfoNotOk(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfoNotOk(id);
        return result;
    }
}
