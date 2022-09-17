package com.example.demo.generator;

import cn.org.atool.fluent.mybatis.metadata.DbType;
import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Column;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import com.example.demo.filter.MybatisFilter;
import org.apache.ibatis.type.JdbcType;


/**
 * @Author zl
 * @CreateTime 2022-09-12 19:06:12
 * @Description FluentMybatis的Entity代码生成
 */

public class Generator {

    //数据源url
    static final String url = "jdbc:mysql://rm-uf6ek21ezr152ems6yo.mysql.rds.aliyuncs.com:3306/sa-token?useUnicode=true$characterEncoding=utf-8";

    //数据库用户名
    static final String username = "zl";

    //数据库密码
    static final String password = "Zl123456";


    public static void main(String[] args) {
        //引用配置类，build方法允许有多个配置类(即多数据源配置)
        // FileGenerator.build(Empty1.class, Empty2.class);

        new FileGenerator()
                // 使用java8的localDateTime类型
                .useJSR310Types()
                .with(Empty1.class)
                .generate();
    }

    // @Tables定义的全局设置, 属性 @Table定义了具体的表设置
    @Tables(
            // 设置数据库类型
            dbType = DbType.MYSQL,
            // 设置驱动类型
            driver = "com.mysql.cj.jdbc.Driver",
            // 数据库schema
            schema = "sa-token",
            // 设置数据库连接信息
            url = url, username = username, password = password,
            // 设置entity类生成src目录，相对于user.dir
            srcDir = "src/main/java",
            // 生成文件的base package路径, 不包含 ".entity", ".dao"部分
            basePack = "com.example.demo",
            //设置dao接口和实现的src目录，相对于user.dir
            daoDir = "src/main/java",
            //设置哪些表要生成Entity文件
            tables = {
                    @Table(
                            value = {"sys_user_role", "sys_user", "sys_role","sys_resource","sys_role_resource"},
                            // 显式指定字段转换属性
                            columns = {
                                    @Column(value = "is_delete", jdbcType = JdbcType.TINYINT, javaType = Integer.class)
                            },
                            // 逻辑删除字段
                            logicDeleted = "is_delete",
                            // 表前缀
                            tablePrefix = {"sys_"},
                            // 默认的查询、更新或添加设置(类似mybatis拦截器)
                            defaults = MybatisFilter.class,
                            // "auto": 显式设置为自增主键, 对应 @TableId(auto=true, seqName="auto")
                            // "user": 显式设置为用户自定义, 对应 @TableId(auto=false, seqName="user")
                            seqName ="auto"
                    ),
            },
            // 生成的实体类字段是否按照数据库字母排序 || true:字母序; false: 数据库定义顺序
            alphabetOrder = false,
            // 生成的数据实体的后缀，默认为Entity
            entitySuffix = ""
    )
    static class Empty1 {

    }

    // @Tables()
    static class Empty2 {

    }


}