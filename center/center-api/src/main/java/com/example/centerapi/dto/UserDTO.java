package com.example.centerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1049633520059727530L;
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

}
