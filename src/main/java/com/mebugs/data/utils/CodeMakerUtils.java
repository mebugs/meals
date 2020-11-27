package com.mebugs.data.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mebugs.sys.entity.SysTableCodeConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  代码生成服务
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@Slf4j
public class CodeMakerUtils {

    public static boolean makeCode(DataSourceConfig dataSourceConfig, SysTableCodeConfig sysTableCodeConfig)
    {
        AutoGenerator generator = new AutoGenerator();

        final String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(sysTableCodeConfig.getAuthor());
        globalConfig.setOpen(false);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);
        //添加要生成的表
        strategyConfig.setInclude(sysTableCodeConfig.getTableName());
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true);

        final PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(sysTableCodeConfig.getPackageName() + sysTableCodeConfig.getModuleName());
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                log.info("BEGIN MAKE CODE");
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + sysTableCodeConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        execute(dataSourceConfig, generator, globalConfig, strategyConfig, packageConfig, cfg, focList);
        return true;
    }

    public static void execute(DataSourceConfig dataSourceConfig, AutoGenerator generator, GlobalConfig globalConfig, StrategyConfig strategyConfig, PackageConfig packageConfig, InjectionConfig cfg, List<FileOutConfig> focList) {
        cfg.setFileOutConfigList(focList);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setGlobalConfig(globalConfig);
        generator.setDataSource(dataSourceConfig);
        generator.setStrategy(strategyConfig);
        generator.setPackageInfo(packageConfig);
        generator.setCfg(cfg);
        generator.setTemplate(templateConfig);
        generator.execute();
    }
}
