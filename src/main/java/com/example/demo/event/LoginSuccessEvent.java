package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description 用户登录成功事件
 */
public class LoginSuccessEvent extends ApplicationEvent {
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
