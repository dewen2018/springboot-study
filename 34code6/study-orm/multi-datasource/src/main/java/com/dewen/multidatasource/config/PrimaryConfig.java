package com.dewen.multidatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * //配置连接工厂 entityManagerFactory
 * //配置 事物管理器  transactionManager
 * //设置持久层所在位置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.dewen.multidatasource.entity.slave"}
        //basePackages = {"xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx"}
)
public class PrimaryConfig {
    @Autowired
    private HibernateProperties hibernateProperties;

    // 自动注入数据源
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Autowired(required = false)
    private JpaProperties jpaProperties;

    // 获取对应的数据库方言
    @Value("${spring.jpa.hibernate.primary-dialect}")
    private String primaryDialect;

    /**
     * 设置实体类所在位置
     *  //设置数据源
     *  //设置数据源属性
     *  //设置实体类所在位置.扫描所有带有 @Entity 注解的类
     *  //.packages("xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx","xxx.xxx.xxx.xxx")
     *  // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
     *  // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
     */
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties())
                .packages("com.dewen.multidatasource.entity.slave")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }
    //设置数据库属性
    private Map<String, Object> getVendorProperties() {
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.dialect",primaryDialect);
        jpaProperties.setProperties(map);
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        return properties;
    }
    /**
     * 配置事物管理器
     */
    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}