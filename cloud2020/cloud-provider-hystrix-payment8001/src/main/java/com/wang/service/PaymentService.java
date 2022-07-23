package com.wang.service;

public interface PaymentService {
    //能正常访问的方法
    String paymentInfoOk(Integer id);
    //不能正常访问的方法
    String paymentInfoNotOk(Integer id);

    String paymentCircuitBreaker(Integer id);
}
