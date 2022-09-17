package com.example.demo.service.impl;

import com.example.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Async("logTaskExecutor")
    @Override
    public void addLog() {
        log.info("{} -- 异步添加日志成功", Thread.currentThread().getName());
    }
}
