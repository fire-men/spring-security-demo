package com.example.demo.service;

import com.example.demo.po.User;
import com.example.demo.vo.UserVo;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-15 11:04:29
 * @Description
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user 用户
     */
    void addUser(User user);


    /**
     * 列出所有用户信息
     *
     * @return {@code List<User>}
     */
    List<User> listAll();

    /**
     * 注销
     */
    void logout();

    /**
     * 登录
     * @param userVo
     * @return
     */
    String login(UserVo userVo) throws Exception;
}
