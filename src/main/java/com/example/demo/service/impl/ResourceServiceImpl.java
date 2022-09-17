package com.example.demo.service.impl;

import cn.org.atool.fluent.mybatis.base.crud.JoinBuilder;
import cn.org.atool.fluent.mybatis.segment.JoinQuery;
import com.example.demo.mapper.ResourceMapper;
import com.example.demo.service.ResourceService;
import com.example.demo.wrapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zl
 * @CreateTime 2022-09-16 16:42:56
 * @Description
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {
    private final ResourceMapper resourceMapper;

    @Override
    public List<String> listPermissionByUsername(String username) {
        ResourceQuery resourceQuery = new ResourceQuery("a")
                .select
                .resourceName("resourceName").end()
                .where
                .type().eq(1).parentId().notNull().end();

        RoleResourceQuery roleResourceQuery = new RoleResourceQuery("b");

        RoleQuery roleQuery = new RoleQuery("c");

        UserRoleQuery userRoleQuery = new UserRoleQuery("d");

        UserQuery userQuery = new UserQuery("e")
                .where
                .username().eq(username).status().eq(0).end();

        return JoinBuilder.from(resourceQuery)
                        .leftJoin(roleResourceQuery)
                        .onApply("a.id=b.resource_id").endJoin()
                        .leftJoin(roleQuery)
                        .onApply("b.role_id=c.id").endJoin()
                        .leftJoin(userRoleQuery)
                        .onApply("c.id=d.role_id").endJoin()
                        .leftJoin(userQuery)
                        .onApply("d.user_id=e.id").endJoin()
                        .build().of(resourceMapper).listObjs();
    }
}
