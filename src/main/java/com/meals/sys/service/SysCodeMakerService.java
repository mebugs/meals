package com.meals.sys.service;

import com.meals.sys.entity.SysTable;
import com.meals.sys.entity.SysTableCodeConfig;
import com.meals.sys.entity.SysTableColumn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  代码生成服务
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Service
public interface SysCodeMakerService {
    /**
     * 根据数据库名称查询所有表属性
     * @param tableName 表名 默认传_下划线全部查询
     * @return
     */
    List<SysTable> findTableList(String tableName);

    /**
     * 查看数据库表字段
     * @return
     */
    List<SysTableColumn> findColumnList(String tableName);

    /**
     * 代码生成
     * @param sysTableCodeConfig
     * @return
     */
    boolean makeCode(SysTableCodeConfig sysTableCodeConfig);
}
