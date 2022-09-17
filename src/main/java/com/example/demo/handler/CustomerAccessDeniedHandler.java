package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.R;
import com.example.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zl
 * @CreateTime 2022-09-16 09:50:51
 * @Description 执行校验controller层方法，校验失败会调用该处理器
 *  @PreAuthorize返回为false时调用该处理器
 */
@Component
@Slf4j
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("您没有访问 {} 的接口权限",request.getRequestURI());
        ResponseUtil.rendString(response, JSON.toJSONString(R.fail(HttpStatus.FORBIDDEN.value(),"您没有访问该接口的权限")));


    }
}
