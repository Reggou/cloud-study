package com.wang.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
