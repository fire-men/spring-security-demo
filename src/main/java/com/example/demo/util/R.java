package com.example.demo.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zl
 * @CreateTime 2022-09-15 14:41:05
 * @Description
 */
@ApiModel(description = "接口统一返回实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T extends Object> implements Serializable {
    private static final long serialVersionUID = -1698647405693929452L;
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAIL_CODE = 500;

    @ApiModelProperty("响应码")
    private Integer code;
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty("响应数据")
    private T data;

    public static <T> R success(String msg, T data) {
        return new R<T>(SUCCESS_CODE, msg, data);
    }

    public static R success(String msg) {
        return new R(SUCCESS_CODE, msg, JSON.parseArray("[]"));
    }

    public static <T> R fail(Integer code, String msg) {
        return new R(code, msg, JSON.parseArray("[]"));
    }

    public static <T> R fail(String msg) {
        return new R(FAIL_CODE, msg, JSON.parseArray("[]"));
    }
}
