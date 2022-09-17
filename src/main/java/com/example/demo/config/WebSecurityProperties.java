package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-16 09:23:17
 * @Description
 */
@ConfigurationProperties(prefix = "security")
@Component
@Data
public class WebSecurityProperties {
    private String[] whiteList;
}
