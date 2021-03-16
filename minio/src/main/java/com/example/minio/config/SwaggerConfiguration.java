package com.example.minio.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfiguration
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2020/11/12 15:29
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean(value = "defaultApi")
    public Docket defaultApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .groupName("minio v1.0")
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.minio.controller"))
                    .paths(PathSelectors.any())
                    .build();
    }

    @Bean(value = "createReatApi2")
    public Docket createReatApi2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo2())
                .groupName("minio v1.1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.minio.group"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                    .title("minio 测试 RESTful APIs")
                    .description("swagger-bootstrat-ui")
                    .termsOfServiceUrl("")
                    .version("v1.0")
                    .contact(new Contact("develoepr",null,"developer@mail.com"))
                    .build();
    }

    public ApiInfo apiInfo2(){
        return new ApiInfoBuilder()
                .title("minio 测试 RESTful APIs")
                .description("swagger-bootstrat-ui")
                .termsOfServiceUrl("")
                .version("v1.1")
                .contact(new Contact("develoepr",null,"developer@mail.com"))
                .build();
    }

}
