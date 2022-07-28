// package com.dewen.config;
//
// import javax.sql.DataSource;
//
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.apache.tomcat.jdbc.pool.PoolProperties;
// import org.mybatis.spring.SqlSessionFactoryBean;
// import org.mybatis.spring.SqlSessionTemplate;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
//
// import net.sf.log4jdbc.Log4jdbcProxyDataSource;
// import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
// import net.sf.log4jdbc.tools.LoggingType;
//
// @Configuration
// @MapperScan(value="com.dewen.jobs.jobdemo.model.dao.first", sqlSessionFactoryRef="firstSqlSessionFactory")
// @EnableTransactionManagement
// public class FirstDataSourceConfiguration {
//
// 	@Bean
// 	@ConfigurationProperties(prefix="spring.datasource.first")
// 	public PoolProperties getFirstPoolProperties() {
// 		return new PoolProperties();
// 	}
//
// 	//DataSource
// 	@Profile("dev")
// 	@Bean(name="firstDataSource")
// 	@ConfigurationProperties(prefix="spring.datasource.first")
// 	public DataSource firstDataSourceDev() {
// 		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(getFirstPoolProperties());
// 		Log4jdbcProxyDataSource proxyDataSource  =  new Log4jdbcProxyDataSource(dataSource);
//
// 		//SQL Formatter
// 		Log4JdbcCustomFormatter customFormatter = new Log4JdbcCustomFormatter();
// 		LoggingType loggingType = LoggingType.MULTI_LINE;
// 		customFormatter.setLoggingType(loggingType);
// 		customFormatter.setSqlPrefix("───────────────────────────────────────────────[ SQL ]\n");
//
// 		proxyDataSource.setLogFormatter(customFormatter);
// 		return proxyDataSource;
// 	}
//
// 	//DataSource
// 	@Profile("prod")
// 	@Bean(name="firstDataSource", destroyMethod="close")
// 	@ConfigurationProperties(prefix="spring.datasource.first")
// 	public DataSource firstDataSourceProd() {
// 		return DataSourceBuilder.create().build();
// 	}
//
// 	//SqlSessionFactory
// 	@Bean(name="firstSqlSessionFactory")
// 	public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
// 		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
// 		sqlSessionFactoryBean.setDataSource(dataSource);
// 		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/mybatis-config.xml"));
// 		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/first/*.xml"));
// 		sqlSessionFactoryBean.setTypeAliasesPackage("com.dewen.jobs.jobdemo.model.vo");
// 		return sqlSessionFactoryBean.getObject();
// 	}
//
// 	//SqlSessionTemplate
// 	@Bean(name="firstSqlSessionTemplate")
// 	public SqlSessionTemplate firstSqlSessionTemplate(SqlSessionFactory firstSqlSessionFactory) throws Exception {
// 		return new SqlSessionTemplate(firstSqlSessionFactory);
// 	}
//
// 	//DataSourceTransactionManager
//     @Bean(name="firstDataSourceTransactionManager")
//     public PlatformTransactionManager firstDataSourceTransactionManager(@Qualifier("firstDataSource") DataSource dataSource) {
//         return new DataSourceTransactionManager(dataSource);
//     }
// }
