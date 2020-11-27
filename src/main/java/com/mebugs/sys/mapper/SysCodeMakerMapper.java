package com.mebugs.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mebugs.sys.entity.SysRole;
import com.mebugs.sys.entity.SysTable;
import com.mebugs.sys.entity.SysTableColumn;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  代码生成服务
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Repository
public interface SysCodeMakerMapper  {
    /**
     * 根据数据库名称查询所有表属性
     * @param tableSchema
     * @return
     */
    @Select("SELECT TABLE_NAME AS tableName,TABLE_COMMENT AS comments,TABLE_SCHEMA AS tableSchema,CREATE_TIME AS createTime FROM information_schema.TABLES WHERE table_schema=#{tableSchema} ORDER BY createTime DESC")
    List<SysTable> findTableList(String tableSchema);

    /**
     * 根据数据库名和表名查询表的列属性
     * @param tableName
     * @return
     */
    @Select("select COLUMN_NAME AS columnName,DATA_TYPE AS dataType,COLUMN_COMMENT AS columnComment,CHARACTER_SET_NAME AS characterSetName,COLUMN_TYPE AS columnType from information_schema.COLUMNS where table_name = #{tableName} and table_schema = #{tableSchema}")
    List<SysTableColumn> findColumnList(String tableName, String tableSchema);
}
