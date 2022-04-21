说起多数据源，一般都来解决那些问题呢，主从 模式 或 业务 比较复杂需要连接不同的分库来支持业务。
网上大都是根据jpa来做多数据源解决方案，要不就是老的spring多数据源解决方案，还有的是利用aop动态切换。



异常
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException:
  ### Error updating database.  Cause: java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.
  ### Cause: java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.
  原因是springboot版本不同原因
###########常规数据库配置
#数据库配置
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
spring.datasource.username = root
spring.datasource.password = root

###########多数据源配置---只有一个jdbc-url不同
#一个test1库和一个test2库，其中test1位主库，在使用的过程中必须指定主库，不然会报错。
spring.datasource.test1.driverClassName = com.mysql.jdbc.Driver
spring.datasource.test1.jdbc-url = jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8
spring.datasource.test1.username = root
spring.datasource.test1.password = root

spring.datasource.test2.driverClassName = com.mysql.jdbc.Driver
spring.datasource.test2.jdbc-url = jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf-8
spring.datasource.test2.username = root
spring.datasource.test2.password = root