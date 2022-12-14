package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.R;
import com.example.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zl
 * @CreateTime 2022-09-16 09:52:45
 * @Description 用户认证异常处理器
 */
@Component
@Slf4j
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("认证失败");
        ResponseUtil.rendString(response, JSON.toJSONString(R.fail(HttpStatus.UNAUTHORIZED.value(),"认证失败")));
    }
}
