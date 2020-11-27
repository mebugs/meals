package com.mebugs.sys.service.impl;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.mebugs.data.utils.CodeMakerUtils;
import com.mebugs.sys.entity.SysTable;
import com.mebugs.sys.entity.SysTableCodeConfig;
import com.mebugs.sys.entity.SysTableColumn;
import com.mebugs.sys.mapper.SysCodeMakerMapper;
import com.mebugs.sys.service.SysCodeMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SysCodeMakerServiceImpl implements SysCodeMakerService {
    @Autowired
    private SysCodeMakerMapper sysCodeMakerMapper;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${schema}")
    private String schema;

    /**
     * 查看数据库表
     * @return
     */
    @Override
    public List<SysTable> findTableList() {
        return sysCodeMakerMapper.findTableList(schema);
    }
    /**
     * 查看数据库表字段
     * @return
     */
    @Override
    public List<SysTableColumn> findColumnList(String tableName) {
        return sysCodeMakerMapper.findColumnList(tableName,schema);
    }
    /**
     * 代码生成
     * @param sysTableCodeConfig
     * @return
     */
    @Override
    public boolean makeCode(SysTableCodeConfig sysTableCodeConfig) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        return CodeMakerUtils.makeCode(dataSourceConfig,sysTableCodeConfig);
    }
}
