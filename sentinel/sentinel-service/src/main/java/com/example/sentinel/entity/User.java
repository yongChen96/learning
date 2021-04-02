package com.example.sentinel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: User
 * @Description: user实体类
 * @Author: yongchen
 * @Date: 2021/4/2 15:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
}
