package com.zhiyi.vestation;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class  CodeGenerate {
    public static void main(String[] args) {
        AutoGenerator codeGenerator = new AutoGenerator();  //创建代码生成器

        //配置代码生成器
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //获取用的目录
        gc.setOutputDir(projectPath+"/src/main/java");   //配置代码生成的位置
        gc.setFileOverride(false);   //是否覆盖原有的代码
        gc.setServiceName("%sService");
        gc.setDateType(DateType.ONLY_DATE);  //日期类型自动填充
        gc.setSwagger2(false);
        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://47.100.210.244:3306/VEStation");
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("1602liuqijun");
        //3.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zhiyi");
        pc.setModuleName("vestation");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setEntity("pojo");
        //4.策略配置值
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("vx_user");     //这里设置逆向工程的表
        sc.setNaming(NamingStrategy.underline_to_camel);  //开启类名驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel); //属性名驼峰命名
        sc.setLogicDeleteFieldName("exist"); //设置逻辑删除
        TableFill tableFill = new TableFill("createDate",FieldFill.INSERT);//设置自动填充列
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(tableFill);
        sc.setTableFillList(list);
        sc.setRestControllerStyle(true); //restFul的Controller的驼峰命名格式
        sc.setControllerMappingHyphenStyle(true); //开启下划线 映射路径   http://localhost:8080/user_id

        codeGenerator.setStrategy(sc);
        codeGenerator.setGlobalConfig(gc);
        codeGenerator.setDataSource(dataSourceConfig);
        codeGenerator.setPackageInfo(pc);
        codeGenerator.execute();  //执行  生成代码

    }
}
