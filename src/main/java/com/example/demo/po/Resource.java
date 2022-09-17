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
 * Resource: 数据映射实体定义
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
    table = "sys_resource",
    schema = "sa-token",
    defaults = MybatisFilter.class,
    suffix = ""
)
public class Resource extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Long id;

  @TableField(
      value = "type",
      desc = "0: 菜单资源 1：按钮资源 2：接口资源"
  )
  private Boolean type;

  @TableField(
      value = "icon",
      desc = "菜单图标"
  )
  private String icon;

  @TableField(
      value = "resource_name",
      desc = "资源名称"
  )
  private String resourceName;

  @TableField(
      value = "resource_desc",
      desc = "资源描述"
  )
  private String resourceDesc;

  @TableField(
      value = "sort",
      desc = "排序"
  )
  private Long sort;

  @TableField(
      value = "parent_id",
      desc = "父id"
  )
  private Long parentId;

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
    return Resource.class;
  }
}
