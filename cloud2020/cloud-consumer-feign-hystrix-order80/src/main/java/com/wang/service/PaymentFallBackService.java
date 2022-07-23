package com.wang.service;

import org.springframework.stereotype.Component;

/**
 * @author wzzmm
 * @since 2022/7/23 21:41
 */
@Component
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "---paymentInfoOk---对方服务器已宕机";
    }

    @Override
    public String paymentInfoNotOk(Integer id) {
        return "---paymentInfoNotOk---对方服务器已宕机";
    }
}
