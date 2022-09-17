package com.example.demo;

import com.example.demo.config.JwtProperties;
import com.example.demo.config.WebSecurityProperties;
import com.example.demo.po.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ResourceService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.SpringUtil;
import com.example.demo.wrapper.UserQuery;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringSecurityTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebSecurityProperties securityProperties;

    @Autowired
    private ResourceService resourceService;

    /**
     * 测试fluentmybatis是否集成成功
     */
    @Test
    void fluentMybatisTest() {
        userMapper.listEntity(new UserQuery()
                        .selectAll())
                .stream()
                .forEach(user -> System.out.println(user));
    }

    /**
     * BcryptPasswordEncoder测试
     */
    @Test
    void bcryptPasswordEncoderTest() {
        String password = "123456";
        String encodePwd = passwordEncoder.encode(password);
        System.out.println("加密后的密码为：" + encodePwd); // $2a$10$DPMIj9x52oK43ItZNZn/eON4XUuXyrSMsPAmHTWB4ioQ17jNxtpVC

        boolean flag = passwordEncoder.matches(password, "$2a$10$DPMIj9x52oK43ItZNZn/eON4XUuXyrSMsPAmHTWB4ioQ17jNxtpVC");
        System.out.println(flag ? "校验成功" : "校验失败");
    }

    /**
     * 添加用户 | 密码加密
     */
    @Test
    void addUserTest() {
        userService.addUser(new User()
                .setUsername("zhangsan")
                .setPassword("123456")
                .setEmail("zhangsan@qq.com")
                .setIphone("18631869326")
                .setNickname("测试...")
                .setSalt("")
                .setCreateBy("aa")
                .setCreateTime(LocalDateTime.now())
        );
    }

    /**
     *  获取自定义配置信息
     */
    @Test
    void getSecurityPropertiesTest(){
        String[] whiteList = securityProperties.getWhiteList();
        System.out.println(Arrays.asList(whiteList));
    }

    /**
     *  SpringUtil测试
     */
    @Test
    void springUtilTest(){
        JwtProperties jwtProperties = SpringUtil.getBean(JwtProperties.class);
        System.out.println(jwtProperties);

        String simpleName = jwtProperties.getExpireTime().getClass().getSimpleName();
        System.out.println(simpleName);
    }

    /**
     *  jwt测试
     */
    @SneakyThrows
    @Test
    void jwtTest() {
        String username = "张三";
        String jwtToken = JwtUtil.createJwtToken(username);
        System.out.println(jwtToken);

        // 解析token
        System.out.println("解析后的token信息为：" + JwtUtil.parseJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLlvKDkuIkiLCJleHAiOjE2NjMzMDEzNDgsImlhdCI6MTY2MzI5OTU0OH0.PwRKUtreWSXAvwvvQpJSZKV7U0Hk7232KHTtw3mHdPhEo609DkOoU1F-XCfw1VHize0Ve8Dhv5J4cyXbKMupLg"));
    }

    /**
     *  查询当前用户所有的按钮权限列表
     */
    @Test
    void queryPermissionsByUsernameTest(){
        String username = "zhangsan";
        List<String> permissions = resourceService.listPermissionByUsername(username);
        System.out.println(permissions);
    }

}
