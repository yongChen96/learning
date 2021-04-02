package com.example.sentinel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CommonResult
 * @Description: 统一返回
 * @Author: yongchen
 * @Date: 2021/4/2 14:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private T data;
    private String msg;
    private int code;
}
