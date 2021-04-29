package com.example.nimbus.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: NimbusJoseJwtDTO
 * @Description: jwtDTO
 * @Author: yongchen
 * @Date: 2021/4/27 15:31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class NimbusJoseJwtDTO implements Serializable {
    private static final long serialVersionUID = -7033083417647131951L;

    @ApiModelProperty(value = "主题")
    private String sub;
    @ApiModelProperty(value = "签发时间")
    private Long iat;
    @ApiModelProperty(value = "过期时间")
    private Long exp;
    @ApiModelProperty(value = "JWT的ID")
    private String jti;
    @ApiModelProperty(value = "用户名称")
    private String username;
    @ApiModelProperty(value = "用户拥有的权限")
    private List<String> authorities;
}
