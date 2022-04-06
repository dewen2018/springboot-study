package com.baidu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//使用xml配置
@ImportResource(locations = {"classpath:uid/cached-uid-spring.xml"})
//@ImportResource(locations = {"classpath:uid/default-uid-spring.xml"})
public class UidConfig {
    // 如果使用类配置，需要修改配置，未实现
    // 使用配置类
//    @Bean(name = "disposableWorkerIdAssigner")
//    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
//        return new DisposableWorkerIdAssigner();
//    }

//    @Bean(name = "defaultUidGenerator")
//    public UidGenerator defaultUidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
//        DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
//        defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
//        return defaultUidGenerator;
//    }

//    @Bean(name = "cachedUidGenerator")
//    public UidGenerator cachedUidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
//        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
//        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
//        return cachedUidGenerator;
//    }
}