package com.example.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @ClassName: RedisTokenStoreConfig
 * @Description: redisTokenStore
 * @Author: yongchen
 * @Date: 2020/11/10 17:08
 **/
@Configuration
public class RedisTokenStoreConfig {

    @Resource
    private RedisConnectionFactory connectionFactory;

    @Bean
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(connectionFactory);
    }



}
