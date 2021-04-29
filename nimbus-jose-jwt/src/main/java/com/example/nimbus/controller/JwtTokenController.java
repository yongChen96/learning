package com.example.nimbus.controller;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.example.nimbus.component.Result;
import com.example.nimbus.dto.NimbusJoseJwtDTO;
import com.example.nimbus.service.JwtTokenService;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: JwtTokenController
 * @Description: JWT令牌管理Controller
 * @Author: yongchen
 * @Date: 2021/4/27 16:01
 **/
@RestController
@RequestMapping("/token")
@Api(value = "JWT令牌管理Controller")
public class JwtTokenController {
    @Resource
    private JwtTokenService jwtTokenService;

    /**
     * @Author: yongchen
     * @Description: 使用对称加密（HMAC）算法生成token
     * @Date: 16:46 2021/4/27
     * @Param: []
     * @return: com.example.nimbus.component.Result<java.lang.String>
     **/
    @GetMapping("/generateToken")
    @ApiOperation(value = "使用对称加密（HMAC）算法生成token")
    public Result<String> generateTokenByHMAC() {
        try {
            NimbusJoseJwtDTO jwtDTO = jwtTokenService.getDefaultInfo();
            String token = jwtTokenService.generateTokenByHMAC(JSON.toJSONString(jwtDTO), SecureUtil.md5("ic"));
            return Result.success(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("生成token失败");
    }

    /**
     * @Author: yongchen
     * @Description: 使用对称加密（HMAC）算法验证token
     * @Date: 16:47 2021/4/27
     * @Param: [token]
     * @return: com.example.nimbus.component.Result<?>
     **/
    @GetMapping("/verify")
    @ApiOperation(value = "使用对称加密（HMAC）算法验证token")
    public Result<?> verifyTokenByHMAC(String token) {
        try {
            NimbusJoseJwtDTO nimbusJoseJwtDTO = jwtTokenService.verifyTokenByHMAC(token, SecureUtil.md5("ic"));
            return Result.success(nimbusJoseJwtDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("token验证失败");
    }

    /**
     * @Author: yongchen
     * @Description: 获取非对称加密（RSA）算法公钥
     * @Date: 17:36 2021/4/27
     * @Param: []
     * @return: com.example.nimbus.component.Result
     **/
    @GetMapping("/publicKey")
    @ApiOperation(value = "获取非对称加密（RSA）算法公钥")
    public Result getRSAPublicKey() {
        RSAKey rsaKey = jwtTokenService.getDefaultRSAKey();
        return Result.success(rsaKey);
    }

    /**
     * @Author: yongchen
     * @Description: 根据非对称加密算法（RSA）生成token
     * @Date: 9:30 2021/4/28
     * @Param: []
     * @return: com.example.nimbus.component.Result<java.lang.String>
     **/
    @GetMapping("/rsaToken")
    @ApiOperation(value = "根据非对称加密算法（RSA）生成token")
    public Result<String> generateTokenByRSA() {
        try {
            NimbusJoseJwtDTO defaultInfo = jwtTokenService.getDefaultInfo();
            RSAKey rsaKey = jwtTokenService.getDefaultRSAKey();
            String token = jwtTokenService.generateTokenByRSA(JSON.toJSONString(defaultInfo), rsaKey);
            return Result.success(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("生成token失败");
    }

    /**
     * @Author: yongchen
     * @Description: 根据非对称加密算法（RSA）解析token
     * @Date: 9:34 2021/4/28
     * @Param: [token]
     * @return: com.example.nimbus.component.Result
     **/
    @GetMapping("/rsaVerify")
    @ApiOperation(value = "根据非对称加密算法（RSA）解析token")
    public Result verifyTokenByRSA(String token){
        try {
            RSAKey rsaKey = jwtTokenService.getDefaultRSAKey();
            NimbusJoseJwtDTO nimbusJoseJwtDTO = jwtTokenService.verifyTokenByRSA(token, rsaKey);
            return Result.success(nimbusJoseJwtDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("token解析失败");
    }
}
