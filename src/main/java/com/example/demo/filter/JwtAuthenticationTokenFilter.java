package com.example.demo.filter;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.config.WebSecurityProperties;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.R;
import com.example.demo.util.ResponseUtil;
import com.example.demo.util.SpringUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


/**
 * jwt身份验证令牌过滤器
 *
 * @author zhangLe
 * @date 2022/09/16
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        String requestPath = request.getRequestURI();
        // 如果为白名单或者登陆操作，直接跳转到controller
        if (ObjectUtil.isEmpty(token) || Collections.contains(Arrays.asList(SpringUtil.getBean(WebSecurityProperties.class).getWhiteList()).iterator(), requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String username = JwtUtil.parseJwtToken(token);
            // 检查当前用户是否已经认证
            if (checkIsAuthentication(username)) {
                filterChain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
            // 表示已经认证过,直接跳转到controller
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof SignatureException) {
                ResponseUtil.rendString(response, JSON.toJSONString(R.fail("token签名不正确")));
            } else if (e instanceof ExpiredJwtException) {
                ResponseUtil.rendString(response, JSON.toJSONString(R.fail("token已失效")));
            } else {
                ResponseUtil.rendString(response, JSON.toJSONString(R.fail("token解析其他异常")));
            }
        }
    }

    public Boolean checkIsAuthentication(String username) {
        return SecurityContextHolder.getContext().getAuthentication() != null ? true : false;
    }
}
