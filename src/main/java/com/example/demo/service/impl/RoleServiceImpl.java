package com.example.demo.service.impl;

import cn.org.atool.fluent.mybatis.base.crud.JoinBuilder;
import cn.org.atool.fluent.mybatis.segment.JoinQuery;
import com.example.demo.po.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import com.example.demo.wrapper.RoleQuery;
import com.example.demo.wrapper.UserRoleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-15 11:29:05
 * @Description
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<Role> listByUserId(Long uid) {
        RoleQuery roleQuery = new RoleQuery("a")
                .select
                .roleName("roleName").end();

        UserRoleQuery userRoleQuery = new UserRoleQuery("b")
                .where
                .userId().eq(uid).end();

        JoinQuery jb = JoinBuilder.from(roleQuery)
                .leftJoin(userRoleQuery)
                .on(l -> l.where.id(), r -> r.where.roleId())
                .endJoin()
                .build();

        return roleMapper.listEntity(jb);
    }
}
