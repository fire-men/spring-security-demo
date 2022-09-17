package com.example.demo.handler;

import com.example.demo.exception.BusinessException;
import com.example.demo.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zl
 * @CreateTime 2022-09-15 14:39:31
 * @Description
 */
@RestController
public class GlobalExceptionHandler {

    // -----------自定义异常---------------------------
    @ExceptionHandler(BusinessException.class)
    public R dealEx(BusinessException ex) {
        return R.fail(ex.getCode(), ex.getMsg());
    }

    // --------------其他异常---------------------------
    @ExceptionHandler(Exception.class)
    public R dealEx(Throwable ex) {
        return R.fail(ex.getMessage());
    }

}
