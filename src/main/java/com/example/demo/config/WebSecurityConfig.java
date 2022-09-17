package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationTokenFilter;
import com.example.demo.handler.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author zl
 * @CreateTime 2022-09-15 10:50:10
 * @Description
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final WebSecurityProperties securityProperties;

    private final UserDetailsService userService;

    private final CustomerLogoutSuccessHandler logoutSuccessHandler;

    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    private final CustomerAccessDeniedHandler accessDeniedHandler;

    private final CustomerAuthenticationEntryPoint authenticationEntryPoint;

    private final CustomerAuthenticationSuccessHandler authenticationSuccessHandler;

    private final CustomerAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 开启跨域
                .cors()
                .and()
                // 关闭跨站请求伪造
                .csrf().disable()
                // 设置异常处理器
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置放行的白名单
                .authorizeRequests().antMatchers(securityProperties.getWhiteList()).permitAll()
                // 其他请求都要认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                // 登录
                .formLogin().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and()
                // 注销
                .logout().logoutUrl("/user/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // 添加过滤器
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


    }

    // 自定义密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
