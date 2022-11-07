package com.dewen;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生成数据库文档
 * dsj
 */
public class GenerateDatabaseDocumnet {
    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/db_seata?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
//        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 1、生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                .fileName("数据库文档")
                // 生成文件路径
                .fileOutputDir("C:\\Users\\Dewen\\Desktop\\")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder().version("1.0.0").description("数据库文档").dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(getProcessConfig()).build();
        // 4、执行生成
        new DocumentationExecute(config).execute();
    }

    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("sys_log", " sys_generator", " sys_permission", " sys_role", " sys_role_permission", " sys_user", " sys_user_permission", " sys_user_role");
        // 忽略表前缀，如忽略a开头的数据库表
        List<String> ignorePrefix = Arrays.asList("a", "t");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_test", "czb_");

        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
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
    }
}