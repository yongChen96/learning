package com.example.nimbus.component;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Description: 统一返回对象
 * @Author: yongchen
 * @Date: 2021/4/27 16:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "数据信息")
    private T data;

    /**
     * 成功返回结果
     *
     * @param data 返回数据
     **/
    public static <T> Result<T> success(T data) {
        return new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 返回数据
     * @param msg 提示信息
     **/
    public static <T> Result<T> success(String msg, T data) {
        return new Result(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回失败信息
     **/
    public static <T> Result<T> fail() {
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMsg(), null);
    }

    /**
     * 返回失败信息
     * @param msg 提示信息
     **/
    public static <T> Result<T> fail(String msg) {
        return new Result(ResultCodeEnum.FAIL.getCode(), msg, null);
    }
}
