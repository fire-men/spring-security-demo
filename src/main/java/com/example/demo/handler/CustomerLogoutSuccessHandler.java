package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.R;
import com.example.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zl
 * @CreateTime 2022-09-15 18:00:36
 * @Description 用户注销成功处理器，通过本demo配置后，不需要自己写注销接口，会自动调用该处理器
 */
@Configuration
@Slf4j
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("token");
        System.out.println("token :"+token);
        System.out.println(SecurityContextHolder.getContext().getAuthentication() == null ? "空" : "不是空的"); // null
        // 清空缓存数据
        SecurityContextHolder.clearContext();

        ResponseUtil.rendSuccessString(httpServletResponse, JSON.toJSONString(R.success("注销成功")));
    }
}
