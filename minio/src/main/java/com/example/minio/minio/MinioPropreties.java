package com.example.minio.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MinioPropreties
 * @Description: minio连接信息
 * @Author: yongchen
 * @Date: 2020/11/11 17:05
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioPropreties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
