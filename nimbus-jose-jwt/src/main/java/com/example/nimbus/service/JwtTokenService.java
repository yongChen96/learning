package com.example.nimbus.service;

import com.example.nimbus.dto.NimbusJoseJwtDTO;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jwk.RSAKey;

import java.text.ParseException;

/**
 * @ClassName: JwtTokenService
 * @Description: JWT服务
 * @Author: yongchen
 * @Date: 2021/4/27 15:37
 **/
public interface JwtTokenService {

    /**
     * @Author: yongchen
     * @Description: HMAC算法生成Token
     * @Date: 15:38 2021/4/27
     * @Param: [payloadStr, secret]
     * @return: java.lang.String
     **/
    String generateTokenByHMAC(String payloadStr, String secret) throws Exception;

    /**
     * @Author: yongchen
     * @Description: HMAC Token解析
     * @Date: 15:39 2021/4/27
     * @Param: [token, secret]
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    NimbusJoseJwtDTO verifyTokenByHMAC(String token, String secret) throws Exception;
    
    /**
     * @Author: yongchen
     * @Description: 获取非对称加密（RSA）算法公钥
     * @Date: 17:22 2021/4/27
     * @Param: []
     * @return: com.nimbusds.jose.jwk.RSAKey
     **/
    RSAKey getDefaultRSAKey();

    /**
     * @Author: yongchen
     * @Description: 使用非对称加密（RSA）算法生成token
     * @Date: 17:41 2021/4/27
     * @Param: [payloadStr, rsaKey]
     * @return: java.lang.String
     **/
    String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException;

    /**
     * @Author: yongchen
     * @Description: 使用非对称加密（RSA）算法验证token
     * @Date: 17:46 2021/4/27
     * @Param: [token, rsaKey]
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    NimbusJoseJwtDTO verifyTokenByRSA(String token, RSAKey rsaKey) throws Exception;

    /**
     * @Author: yongchen
     * @Description: 获取默认的NimbusJoseJwtDTO
     * @Date: 15:57 2021/4/27
     * @Param: []
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    NimbusJoseJwtDTO getDefaultInfo();
}
