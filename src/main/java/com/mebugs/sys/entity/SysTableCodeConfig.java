package com.mebugs.sys.entity;

import lombok.Data;

/**
 * <p>
 *  代码生成服务 代码生成配置
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Data
public class SysTableCodeConfig {
    /**
     * 包名
     */
    private String packageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comments;
}
