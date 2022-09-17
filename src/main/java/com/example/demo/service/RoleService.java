package com.example.demo.service;

import com.example.demo.po.Role;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-15 11:27:28
 * @Description
 */
public interface RoleService {

    /**
     * 通过用户id查询角色列表
     *
     * @param uid uid
     * @return {@code List<Role>}
     */
    List<Role> listByUserId(Long uid);
}
