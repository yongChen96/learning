package com.example.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @Author: yongchen
 * @Date: 2020/10/22 10:46
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class User {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
