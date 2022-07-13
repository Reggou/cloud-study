package com.wang.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Feign是一个声明式的Web服务客户端，让编写Web服务客户端变得非常容易，
 *      只需创建一个接口并在接口上添加注解即可
 *
 * @author wzm
 * @since 2022/6/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String  message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
