package com.dewen;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.ArrayList;

@SpringBootTest
class ProjectnameApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 文档生成
     */
    @Test
    void documentGeneration() {
        String fileOutputDir = "C:\\Users\\admin1\\Desktop\\";
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://192.168.9.19:3306/sqt_jxc_bs?useUnicode=true&characterEncoding=UTF8");
        hikariConfig.setUsername("mysqldatabackup");
        hikariConfig.setPassword("9ox654321");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(fileOutputDir)
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName("自定义文件名称").build();

        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");

        ArrayList<String> designatedTableName = new ArrayList<>();
        designatedTableName.add("goods");
        designatedTableName.add("customer");
        designatedTableName.add("supplier");
        designatedTableName.add("warehouse");
        designatedTableName.add("warehouse_item");

        designatedTableName.add("purchase_order");
        designatedTableName.add("purchase_order_item");
        designatedTableName.add("purchase_order_delivery_plan");
        designatedTableName.add("purchase_order_delivery_plan_item");
        designatedTableName.add("purchase_order_receive_notifi");
        designatedTableName.add("purchase_order_receive_notifi_item");
        designatedTableName.add("purchase_in");
        designatedTableName.add("purchase_in_item");
        designatedTableName.add("purchase_invoice");
        designatedTableName.add("purchase_invoice_item");

        designatedTableName.add("sale_order");
        designatedTableName.add("sale_order_item");
        designatedTableName.add("sale_order_delivery_plan");
        designatedTableName.add("sale_order_delivery_plan_item");
        designatedTableName.add("sale_out");
        designatedTableName.add("sale_out_item");
        designatedTableName.add("sale_invoice");
        designatedTableName.add("sale_invoice_item");

        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(designatedTableName)
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig)
                .build();
        //执行生成
        new DocumentationExecute(config).execute();
    }
}
