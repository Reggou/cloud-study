package com.wang.service;

import com.wang.entities.CommonResult;
import com.wang.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author wzzmm
 * @since 2022/10/6 13:01
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(443,"服务降级openFeign兜底",new Payment(id,"errorSerial"));
    }
}
