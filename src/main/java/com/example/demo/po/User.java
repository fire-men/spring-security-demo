package com.example.demo.po;

import cn.org.atool.fluent.mybatis.annotation.*;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.example.demo.filter.MybatisFilter;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
        chain = true
)
@EqualsAndHashCode(
        callSuper = false
)
@AllArgsConstructor
@NoArgsConstructor
@FluentMybatis(
        table = "sys_user",
        schema = "sa-token",
        defaults = MybatisFilter.class,
        suffix = ""
)
public class User extends RichEntity implements UserDetails {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField(
            value = "username",
            desc = "账号"
    )
    private String username;

    @TableField(
            value = "password",
            desc = "密码"
    )
    private String password;

    @TableField("salt")
    private String salt;

    @TableField(
            value = "nickname",
            desc = "昵称"
    )
    private String nickname;

    @TableField(
            value = "iphone",
            desc = "手机号"
    )
    private String iphone;

    @TableField(
            value = "email",
            desc = "电子邮箱"
    )
    private String email;

    @TableField(
            value = "status",
            desc = "0：正常 1：锁定  2：失效"
    )
    private Integer status;

    @TableField(
            value = "is_delete",
            insert = "0",
            desc = "0：未删除  1：已删除"
    )
    @LogicDelete
    private Integer isDelete;

    @TableField(
            value = "create_by",
            desc = "创建人"
    )
    private String createBy;

    @TableField(
            value = "create_time",
            desc = "创建时间"
    )
    private LocalDateTime createTime;

    @TableField(
            value = "update_by",
            desc = "修改人"
    )
    private String updateBy;

    @TableField(
            value = "update_time",
            desc = "修改时间"
    )
    private LocalDateTime updateTime;

    // 非数据库字段
    @NotField
    private Collection<GrantedAuthority> authorities;

    @Override
    public final Class entityClass() {
        return User.class;
    }

    // ---------------------Spring Security 相关方法----------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status != 2;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 0;
    }
}
