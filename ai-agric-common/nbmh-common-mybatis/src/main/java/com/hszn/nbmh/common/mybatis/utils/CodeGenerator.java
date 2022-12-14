package com.hszn.nbmh.common.mybatis.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis自动生成代码
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();


        /* ******************************* 需要修改处 start **********************************/
        // 作者
        gc.setAuthor("yuan");
        // 数据库密码
        dsc.setPassword("123456");
        // 数据库url
        dsc.setUrl("jdbc:mysql://localhost:3306/nbmh?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // 需要创建的表名，什么都不填生成所有
        strategy.setInclude("nbmh_user_address");
        /* ******************************* 需要修改处 end **********************************/

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.hszn.nbmh.user");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 全局配置
        String module = "ai-agric-user";
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/" + module + "/src/main/java");
        gc.setBaseResultMap(true);
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        mpg.setDataSource(dsc);

        // 策略配置
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + module + "/src/main/resources/mapper/"
                         + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置
        TemplateConfig tc = new TemplateConfig();
        tc.setServiceImpl("/templates/serviceImpl.java");
        tc.setXml(null);
        mpg.setTemplate(tc);

        mpg.execute();
    }
}
