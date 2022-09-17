package com.example.demo.listener;

import cn.hutool.aop.ProxyUtil;
import com.example.demo.event.LoginSuccessEvent;
import com.example.demo.service.LogService;
import com.example.demo.util.SpringUtil;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description 自定义登录事件 --> 替换SpringSecurity登录成功处理器
 */
@Component
@Slf4j
public class LoginEventListener {

    @EventListener(classes = {LoginSuccessEvent.class})
    public void handleSuccessEvent(ApplicationEvent event) {
        UserVo msg = (UserVo) event.getSource();
        log.info("【登录事件监听器】--> {}", msg);
        // 异步添加登录操作日志
        SpringUtil.getBean(LogService.class).addLog();

        // ((LoginEventListener) AopContext.currentProxy()).addLog();


    }

    /**
     * 该方法必须通过代理对象调用，否则不会异步执行任务
     */
    @Async("logTaskExecutor")
    public void addLog() {
        log.info("{} -- 异步添加日志成功", Thread.currentThread().getName());
    }
}
