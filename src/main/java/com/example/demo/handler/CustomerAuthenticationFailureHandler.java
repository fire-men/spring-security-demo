package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.R;
import com.example.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zl
 * @CreateTime 2022-09-16 10:00:06
 * @Description 通过WebSecurityConfig配置，认证失败后，该处理器没有反应
 *  自己创建认证失败处理器
 */
@Component
@Slf4j
public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("【用户】：{} 认证失败", SecurityContextHolder.getContext().getAuthentication().getName());

        if (exception instanceof BadCredentialsException) {
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail("用户名和密码不正确")));
        } else if (exception instanceof LockedException) {
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail("账号被锁定")));
        } else if (exception instanceof DisabledException) {
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail( "账号不可用")));
        } else if (exception instanceof AccountExpiredException) {
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail("账号已过期")));
        } else if (exception instanceof UsernameNotFoundException) {
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail("用户名不存在")));
        }else{
            ResponseUtil.rendString(response, JSON.toJSONString(R.fail("登录失败")));

        }

    }
}
