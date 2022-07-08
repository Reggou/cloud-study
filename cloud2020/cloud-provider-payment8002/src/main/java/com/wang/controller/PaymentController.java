package com.wang.controller;

import com.wang.entities.CommonResult;
import com.wang.entities.Payment;
import com.wang.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author wzm
 * @since 2022/6/12
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.createPayment(payment);
        log.info(">>插入结果:"+result);
        if (result > 0){
            return new CommonResult(200,"插入成功,serverPort->"+serverPort,result);
        }else {
            return new CommonResult(300,"插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info(">>插入结果:"+payment);
        if (payment != null){
            return new CommonResult(200,"查询成功,serverPort->"+serverPort,payment);
        }else {
            return new CommonResult(300,"查询出错,查询id为:"+id);
        }
    }
}