// package com.dewen.config;
//
// import javax.sql.DataSource;
//
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.mybatis.spring.SqlSessionFactoryBean;
// import org.mybatis.spring.SqlSessionTemplate;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.Profile;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
//
// /**
//  * DataSource For Spring Batch
//  *
//  */
//
// @Configuration
// @EnableTransactionManagement
// public class SpringBatchDatabaseConfiguration {
// 	//DataSource
// 	@Profile("dev")
// 	@Bean(name="dataSource", destroyMethod="close")
// 	@Primary
// 	@ConfigurationProperties(prefix="spring.datasource.hikari")
// 	public DataSource dataSourceDev() {
// 		return DataSourceBuilder.create().build();
// 	}
//
// 	//DataSource
// 	@Profile("prod")
// 	@Bean(name="dataSource", destroyMethod="close")
// 	@Primary
// 	@ConfigurationProperties(prefix="spring.datasource.hikari")
// 	public DataSource dataSourceProd() {
// 		return DataSourceBuilder.create().build();
// 	}
//
// 	//SqlSessionFactory
// 	@Bean(name="sqlSessionFactory")
// 	@Primary
// 	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
// 		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
// 		sqlSessionFactoryBean.setDataSource(dataSource);
// 		return sqlSessionFactoryBean.getObject();
// 	}
//
// 	//SqlSessionTemplate
// 	@Bean(name="sqlSessionTemplate")
// 	@Primary
// 	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
// 		return new SqlSessionTemplate(sqlSessionFactory);
// 	}
// }
