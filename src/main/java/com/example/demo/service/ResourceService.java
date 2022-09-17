package com.example.demo.service;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-16 16:41:56
 * @Description
 */
public interface ResourceService {
    List<String> listPermissionByUsername(String username);
}
