package com.example.demo.config;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author zl
 * @CreateTime 2022-09-16 10:45:25
 * @Description
 */
@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtProperties {
    private String secret;
    private Long expireTime;
}
