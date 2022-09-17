package com.example.demo.config;

import com.example.demo.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author zl
 * @CreateTime 2022-09-16 16:34:01
 * @Description 自定义接口权限和菜单权限
 */
@Component("ps")
@RequiredArgsConstructor
public class PermissionService {
    private final ResourceService resourceService;

    public Boolean isHasPermission(String permission) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 查询当前用户拥有的按钮权限列表
        if (resourceService.listPermissionByUsername(username).contains(permission)) {
            return true;
        }
        return false;
    }


}
