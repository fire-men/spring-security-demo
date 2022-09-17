package com.example.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.demo.exception.BusinessException;
import com.example.demo.po.User;
import com.example.demo.util.R;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-15 11:48:51
 * @Description
 */
@Api(tags = "用户管理")
@ApiSupport(order = 100)
@RestController
@RequestMapping("user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserVo", name = "userVo", value = "")
    })
    @ApiOperation(value = "登录操作")
    @ApiOperationSupport(order = 100)
    @PostMapping("/login")
    public R<User> login(@RequestBody UserVo userVo) throws Exception {
        if (ObjectUtil.isEmpty(userVo)) {
            log.info("user : {}", userVo);
            throw new BusinessException(5001, "登录操作用户名和密码不能为空");
        }
        if (ObjectUtil.isEmpty(userVo.getUsername()) || ObjectUtil.isEmpty(userVo.getPassword())) {
            log.info("username : {},password : {}", userVo.getUsername(), userVo.getPassword());
            throw new BusinessException(5001, "登录操作用户名和密码不能为空");
        }
        String token = userService.login(userVo);
        HashMap result = new HashMap();
        result.put("token", token);
        return R.success("登录成功", result);
    }

    /**
     * 该注销方法不会执行，会调用注销成功后的处理器方法(通过自定义配置)
     * @return
     */
//    @PostMapping("/logout")
//    public R logout() {
//        userService.logout();
//        return R.success("注销成功");
//    }

    @PreAuthorize("@ps.isHasPermission('sys:user:list1')")
    @ApiOperation(value = "查询用户列表")
    @ApiOperationSupport(order = 200)
    @GetMapping("/list")
    public R<List<User>> list() {
        return R.success("查询用户列表成功", userService.listAll());
    }

    @PreAuthorize("@ps.isHasPermission('sys:user:select')")
    @ApiOperation(value = "查询用户列表")
    @ApiOperationSupport(order = 300)
    @GetMapping("/list2")
    public R<List<User>> list2() {
        return R.success("查询用户列表成功", userService.listAll());
    }
}
