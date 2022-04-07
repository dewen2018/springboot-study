package com.dewen.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
@MapperScan(value="com.dewen.model.dao.second", sqlSessionFactoryRef="secondSqlSessionFactory")
@EnableTransactionManagement
public class SecondDataSourceConfiguration {
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.second")
	public PoolProperties getSecondPoolProperties() {
		return new PoolProperties();
	}
	
	//DataSource
	@Profile("dev")
	@Bean(name="secondDataSource")
	@ConfigurationProperties(prefix="spring.datasource.second")
	public DataSource secondDataSourceDev() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(getSecondPoolProperties());
		Log4jdbcProxyDataSource proxyDataSource  =  new Log4jdbcProxyDataSource(dataSource);
		
		//SQL Formatter
		Log4JdbcCustomFormatter customFormatter = new Log4JdbcCustomFormatter();
		LoggingType loggingType = LoggingType.MULTI_LINE;
		customFormatter.setLoggingType(loggingType);
		customFormatter.setSqlPrefix("───────────────────────────────────────────────[ SQL ]\n");
		
		proxyDataSource.setLogFormatter(customFormatter);		
		return proxyDataSource;
	}
	
	//DataSource
	@Profile("prod")
	@Bean(name="secondDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="spring.datasource.second")
	public DataSource secondDataSourceProd() {
		return DataSourceBuilder.create().build();
	}
	
	//SqlSessionFactory
	@Bean(name="secondSqlSessionFactory")
	public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/second/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.dewen.model.vo");
		return sqlSessionFactoryBean.getObject();
	}
	
	//SqlSessionTemplate
	@Bean(name="secondSqlSessionTemplate")
	public SqlSessionTemplate secondSqlSessionTemplate(SqlSessionFactory secondSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(secondSqlSessionFactory);
	}
	
	//DataSourceTransactionManager
    @Bean(name="secondDataSourceTransactionManager")
    public PlatformTransactionManager secondDataSourceTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
