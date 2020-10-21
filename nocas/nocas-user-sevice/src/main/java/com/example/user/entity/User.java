package com.example.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserService
 * @Description: 用户信息实体类
 * @Author: yongchen
 * @Date: 2020/10/21 15:42
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 3756784275022647809L;

    /**
     * 主键
     **/
    private String id;

    /**
     * 姓名
     **/
    private String name;

    /**
     * 年龄
     **/
    private int age;

    /**
     * 性别
     **/
    private String sex;
}
