package com.example.demo.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zl
 * @CreateTime 2022-09-15 10:31:28
 * @Description
 */
@Configuration
public class FluentMybatisConfig {

    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
