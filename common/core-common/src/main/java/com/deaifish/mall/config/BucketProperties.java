package com.deaifish.mall.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description Bucket配置文件类
 *
 * @author DEAiFISH
 * @date 2023/11/26 23:31
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.bucket")
public class BucketProperties {
    public String bucketPath;
    public String bucketName;
    public String accessKeyId;
    public String accessKeySecret;
    public String endpoint;
}
