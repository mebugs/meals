package com.mebugs.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器 基础版
 * 后续集成到页面中
 * 实现MyBatis-Plus
 */
public class CodeMaker {

    private static String URL = "jdbc:mysql://localhost:3306/meal?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
    private static String USERNAME = "meal";
    private static String PASSWORD = "admin123";
    //package地址
    private static String module = "sys";
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        final String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("米虫先生/mebugs.com");
        globalConfig.setOpen(false);
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);
        //添加要生成的表
        strategyConfig.setInclude(
                "sys_user",
                "sys_role",
                "sys_user_role"
        );
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true);

        final PackageConfig  packageConfig = new PackageConfig();
        packageConfig.setParent("com.mebugs." + module);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
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
                return projectPath + "/src/main/resources/mapper/" + module + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
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
