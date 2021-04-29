package com.example.nimbus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: Knife4jConfig
 * @Description: knife4j 接口文档配置
 * @Author: yongchen
 * @Date: 2021/4/27 16:26
 **/
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    @Bean
    public Docket defaultApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .groupName("Nimbus-jose-jwt")
                    .select()
                    //为当下包生成swagger文档
                    .apis(RequestHandlerSelectors.basePackage("com.example.nimbus.controller"))
                    .paths(PathSelectors.any())
                    .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                    .title("Nimbus-jose-jwt 接口文档")
                    .description("Nimbus-jose-jwt")
                    .version("v0.1")
                    .build();
    }
}
