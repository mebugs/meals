package com.mebugs.sys.entity;

import lombok.Data;

/**
 * <p>
 *  代码生成服务 数据库表实体
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Data
public class SysTable {
    /**
     * 名称
     */
    private String tableName;
    /**
     * 备注
     */
    private String comments;
    /**
     * 归属库
     */
    private String tableSchema;
    /**
     * 创建时间
     */
    private String createTime;
}
