package com.example.demo.po;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.example.demo.filter.MybatisFilter;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserRole: 数据映射实体定义
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
    table = "sys_user_role",
    schema = "sa-token",
    defaults = MybatisFilter.class,
    suffix = ""
)
public class UserRole extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Long id;

  @TableField("user_id")
  private Long userId;

  @TableField("role_id")
  private Long roleId;

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

  @Override
  public final Class entityClass() {
    return UserRole.class;
  }
}
