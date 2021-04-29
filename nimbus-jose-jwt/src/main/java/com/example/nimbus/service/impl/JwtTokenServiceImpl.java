package com.example.nimbus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.example.nimbus.dto.NimbusJoseJwtDTO;
import com.example.nimbus.service.JwtTokenService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;
import sun.security.rsa.RSASignature;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: JwtTokenServiceImpl
 * @Description: nimbus-jose-jwt服务
 * @Author: yongchen
 * @Date: 2021/4/27 15:40
 **/
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    /**
     * @Author: yongchen
     * @Description: HMAC算法生成Token
     * @Date: 15:47 2021/4/27
     * @Param: [payloadStr, secret]
     * @return: java.lang.String
     **/
    @Override
    public String generateTokenByHMAC(String payloadStr, String secret) throws Exception {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到payLoad中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        MACSigner macSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(macSigner);
        return jwsObject.serialize();
    }

    /**
     * @Author: yongchen
     * @Description: token解析
     * @Date: 15:55 2021/4/27
     * @Param: [token, secret]
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    @Override
    public NimbusJoseJwtDTO verifyTokenByHMAC(String token, String secret) throws Exception {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new Exception("token签名不合法");
        }
        String str = jwsObject.getPayload().toString();
        NimbusJoseJwtDTO nimbusJoseJwtDTO = JSONUtil.toBean(str, NimbusJoseJwtDTO.class);
        if (nimbusJoseJwtDTO.getExp() < new Date().getTime()) {
            throw new Exception("token已过期");
        }
        return nimbusJoseJwtDTO;
    }

    /**
     * @Author: yongchen
     * @Description: 获取非对称加密（RSA）算法公钥
     * @Date: 17:22 2021/4/27
     * @Param: []
     * @return: com.nimbusds.jose.jwk.RSAKey
     **/
    @Override
    public RSAKey getDefaultRSAKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456.".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "123456.".toCharArray());
        //获取RSA公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //获取RSA私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }

    /**
     * @Author: yongchen
     * @Description: 使用非对称加密（RSA）算法生成token
     * @Date: 17:40 2021/4/27
     * @Param: []
     * @return: java.lang.String
     **/
    @Override
    public String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到payLoad中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建RSA签名器
        JWSSigner jwsSigner = new RSASSASigner(rsaKey, true);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    /**
     * @Author: yongchen
     * @Description: 使用非对称加密（RSA）算法验证token
     * @Date: 17:41 2021/4/27
     * @Param: []
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    @Override
    public NimbusJoseJwtDTO verifyTokenByRSA(String token, RSAKey rsaKey) throws Exception {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey key = rsaKey.toPublicJWK();
        //使用RSA公钥创建RSA验证器
        RSASSAVerifier rsassaVerifier = new RSASSAVerifier(key);
        if (!jwsObject.verify(rsassaVerifier)){
            throw new Exception("token签名不合法");
        }
        String str = jwsObject.getPayload().toString();
        NimbusJoseJwtDTO nimbusJoseJwtDTO = JSONUtil.toBean(str, NimbusJoseJwtDTO.class);
        if (nimbusJoseJwtDTO.getExp() < new Date().getTime()){
            throw new Exception("token已过期");
        }
        return nimbusJoseJwtDTO;
    }

    /**
     * @Author: yongchen
     * @Description: 获取默认的NimbusJoseJwtDTO
     * @Date: 15:57 2021/4/27
     * @Param: []
     * @return: com.example.nimbus.dto.NimbusJoseJwtDTO
     **/
    @Override
    public NimbusJoseJwtDTO getDefaultInfo() {
        Date date = new Date();
        Date exp = DateUtil.offsetSecond(date, 60 * 60);
        return NimbusJoseJwtDTO.builder()
                .sub("abc")
                .iat(date.getTime())
                .exp(exp.getTime())
                .jti(UUID.randomUUID().toString())
                .username("abc")
                .authorities(CollUtil.toList("ADMIN"))
                .build();
    }
}
