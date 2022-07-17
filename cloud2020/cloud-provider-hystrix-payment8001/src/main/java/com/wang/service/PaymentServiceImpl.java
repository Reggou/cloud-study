package com.wang.service;

import org.springframework.stereotype.Service;

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

    @Override
    public String paymentInfoNotOk(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+",paymentInfoNotOk"+id+"\t"+"耗时3s";
    }
}
