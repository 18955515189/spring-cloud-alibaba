package com.springcloud.alibaba;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description mybatis-plus代码生成框架
 * @Author 飞翔的胖哥
 * @SINCE 2020/1/12 0012 21:02
 * @Version 1.0.0
 **/
public class MybatisGenerator {

    private static final String project_package="com.springcloud.alibaba";
    private static final String URL="jdbc:postgresql://192.168.189.131:5432/postgres";
    private static final String DRIVER_NAME="org.postgresql.Driver";
    private static final String USER_NAME="postgres";
    private static final String USER_PASSWORD="123456";

    public static void main(String[] args) {
        MybatisGenerator g = new MybatisGenerator();
        boolean startWithI = false;
        String packageName = "author";
        String tableName = "author";
        String tableFix = "";
        g.generateByTables(startWithI, packageName,tableFix, tableName);

    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param packageName           表名
     * @param tableFix              表前缀
     * @param tableNames            表名
     */
    private void generateByTables(boolean serviceNameStartWithI, String packageName,String tableFix, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableFix,tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自定义设置
        //自动生成
        InjectionConfig injectionConfig = getInjectionConfig(packageName);
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig,injectionConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     */
    private void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig,InjectionConfig injectionConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(new TemplateConfig().setXml(null))
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     */
    private PackageConfig getPackageConfig(String packageName) {


        return new PackageConfig()
                .setModuleName(packageName)
                .setParent(project_package);
    }


    /**
     * 自定义配置
     * @return
     */
    private InjectionConfig getInjectionConfig(String packageName){
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/src/main/resources/mapper/" + packageName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     */
    private GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {

        // 设置全局配置变量
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java")
                .setAuthor("weizhouck")
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setOpen(false)
                .setFileOverride(true)
                .setSwagger2(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }

        return globalConfig;
    }

    /**
     * 策略配置
     *
     * @param tableNames
     * @return
     */
    private StrategyConfig getStrategyConfig(String tableFix,String... tableNames) {

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                // 公共父类
                //.setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true)
                .setInclude(tableNames)
                .setTablePrefix(tableFix);
        return strategy;
    }

    /**
     * 数据源设置（自定义）
     *
     * @return
     */
    private DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USER_NAME);
        dataSourceConfig.setPassword(USER_PASSWORD);
        return dataSourceConfig;
    }
}
