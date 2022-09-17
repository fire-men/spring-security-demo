package com.example.demo.filter;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import cn.org.atool.fluent.mybatis.base.model.SqlOp;

import java.util.function.Supplier;

/**
 * @Author zl
 * @CreateTime 2022-09-13 14:18:36
 * @Description 默认查询, 更新条件设置，类似于mybatis拦截器
 */
public class MybatisFilter implements IDefaultSetter {
    private static final String DEL_FLAG = "is_delete";

    @Override
    public Supplier<Object> pkGenerator(IEntity entity) {
        return IDefaultSetter.super.pkGenerator(entity);
    }

    @Override
    public void setInsertDefault(IEntity entity) {
        IDefaultSetter.super.setInsertDefault(entity);
    }

    /* -----------------------------------更新和查询添加上逻辑删除字段条件----------------------------------------- */
    @Override
    public void setQueryDefault(IQuery query) {
        query.where()
                .apply(DEL_FLAG, SqlOp.EQ, 0);
    }

    @Override
    public void setUpdateDefault(IUpdate updater) {
        updater.where()
                .apply(DEL_FLAG, SqlOp.EQ, 0);
    }
}
