package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.demo.event.LoginSuccessEvent;
import com.example.demo.po.Role;
import com.example.demo.po.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.SpringUtil;
import com.example.demo.vo.UserVo;
import com.example.demo.wrapper.UserQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-15 11:04:39
 * @Description
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;

    private final RoleService roleService;

    private final ApplicationEventPublisher eventPublisher;

    // private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息
        List<User> users = new UserQuery()
                .selectAll()
                .where
                .username().eq(username).end()
                .of(userMapper)
                .listEntity();

        if (ObjectUtil.isNotEmpty(users) && users.size() > 0) {
            User user = users.get(0);
            // 获取当前用户角色信息
            List<Role> roles = roleService.listByUserId(user.getId());
            // 填充到user中
            List<GrantedAuthority> authorities = new ArrayList();
            if (ObjectUtil.isNotEmpty(roles) && roles.size() > 0) {
                for (int i = 0; i < roles.size(); i++) {
                    authorities.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
                }
                user.setAuthorities(authorities);
                return user;
            }else{
                log.info("当前用户没有绑定角色");
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public List<User> listAll() {
        return new UserQuery()
                .selectAll()
                .of(userMapper)
                .listEntity();
    }

    @Override
    public String login(UserVo userVo) throws Exception {
        // 认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userVo.getUsername(), userVo.getPassword(), null);
        Authentication authenticate = SpringUtil.getBean(AuthenticationManager.class).authenticate(usernamePasswordAuthenticationToken);

        if (ObjectUtil.isEmpty(authenticate)) {
            throw new BusinessException(500, "认证失败");
        }
        // 发布认证成功事件
        eventPublisher.publishEvent(new LoginSuccessEvent(userVo));

        String username = authenticate.getName();
        return JwtUtil.createJwtToken(username);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
