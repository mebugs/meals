package com.meals.sys.entity;

import lombok.Data;

/**
 * <p>
 *  代码生成服务 数据库表字段
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Data
public class SysTableColumn {
    /**
     * 列表
     */
    private String columnName;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 备注
     */
    private String columnComment;
    /**
     * 字符集
     */
    private String characterSetName;
    /**
     * 列字段类型
     */
    private String columnType;
}
