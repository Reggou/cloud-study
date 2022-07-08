package com.wang.service;

import com.wang.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    //增
    int createPayment(Payment payment);
    //查
    Payment getPaymentById(@Param("id") Long id);
}
