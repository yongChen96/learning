package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserDTO
 * @Description: UserDTO
 * @Author: yongchen
 * @Date: 2021/5/6 14:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -693529822573370494L;

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

}
