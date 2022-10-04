package com.wang.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wang.entities.CommonResult;
import com.wang.entities.Payment;

/**
 * 自定义流控处理
 *
 * @author wzzmm
 * @since 2022/10/4 12:15
 */

public class CustomerBlockHandler {
    public static CommonResult<Payment> handlerException1(BlockException exception) {
        return new CommonResult<>(433, "全局服务降级", new Payment(2020L, "serial003"));
    }
    public static CommonResult<Payment> handlerException2(BlockException exception) {
        return new CommonResult<>(422, "全局服务降级", new Payment(2020L, "serial003"));
    }
}
