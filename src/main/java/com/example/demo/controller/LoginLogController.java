package com.example.demo.controller;

import com.example.demo.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zl
 * @CreateTime 2022-09-16 16:09:10
 * @Description
 */
@RestController
@RequestMapping("loginLog")
public class LoginLogController {

    @GetMapping("/queryOnlineUserCount")
    public R queryOnlineUserCount(){
        return R.success("查询成功",2000);
    }
}
