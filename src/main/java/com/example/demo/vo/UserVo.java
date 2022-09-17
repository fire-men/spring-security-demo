package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zl
 * @CreateTime 2022-09-15 14:28:55
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1184793111740272699L;

    private String username;
    private String password;
}
