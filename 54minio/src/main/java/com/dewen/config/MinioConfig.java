package com.dewen.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    // endPoint是一个URL，域名，IPv4或者IPv6地址
    private String endpoint;

    // TCP/IP端口号
    private int port;

    private String accessKey;

    private String secretKey;

    // 如果是true，则用的是https而不是http,默认值是true
    private Boolean secure;

    // 默认存储桶
    private String bucketName;

    @Bean
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
        MinioClient minioClient = new MinioClient(endpoint, port, accessKey, secretKey, secure);
        return minioClient;
    }
}