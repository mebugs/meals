package com.mebugs.sys.service;

import com.mebugs.sys.entity.SysTable;
import com.mebugs.sys.entity.SysTableCodeConfig;
import com.mebugs.sys.entity.SysTableColumn;
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
     * 查看数据库表
     * @return
     */
    List<SysTable> findTableList();
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
