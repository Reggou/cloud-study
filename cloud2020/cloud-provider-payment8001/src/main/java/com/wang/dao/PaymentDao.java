package com.wang.dao;

import com.wang.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wzm
 * @since 2022/6/12
 */
@Repository
public interface PaymentDao {
    //增
    int createPayment(Payment payment);
    //查
    Payment getPaymentById(@Param("id") Long id);
}
