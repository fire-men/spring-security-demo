package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zl
 * @CreateTime 2022-09-16 09:57:41
 * @Description 通过WebSecurityConfig配置，认证成功后，该处理器没有反应
 *  自己写认证成功事件处理器
 */
@Component
@Slf4j
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("用户 : {} 认证成功",authentication.getPrincipal());

        // 异步写入日志

        // 写入缓存
    }
}
