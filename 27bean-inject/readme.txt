@Configuration + @Bean
@ComponentScan + @Component
@Import 配合接口进行导入
使用FactoryBean。
实现BeanDefinitionRegistryPostProcessor进行后置处理