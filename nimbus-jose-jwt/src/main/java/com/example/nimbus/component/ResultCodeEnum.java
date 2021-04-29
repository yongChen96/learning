package com.example.nimbus.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ResultCodeEnum
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/4/27 16:09
 **/
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(0, "success"),
    FAIL(-1,"fail");

    private final Integer code;
    private final String msg;
}
