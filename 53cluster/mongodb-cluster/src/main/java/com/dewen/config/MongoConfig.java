package com.dewen.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * mongo配置类
 */
@Configuration
public class MongoConfig {

    @Autowired
    private MongoBaseConfiguration baseConfiguration;
    
    @Autowired
    private MongoWarnConfiguration warnConfiguration;
    
    
    @Bean
    public MongoDbFactory mongoDbFactory(MongoConfiguration  config) throws Exception {
        MongoClientURI uri = new MongoClientURI(config.getUri());
        MongoClient mongoClient = new MongoClient(uri);
        
        MongoDbFactory dbFactory = new SimpleMongoDbFactory(mongoClient, config.getDatabase());
        return dbFactory;
    }
    
    @Bean(name="baseMongoTemplate")
    public MongoTemplate baseMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(baseConfiguration));
    }
    
    
    @Bean(name="warnMongoTemplate")
    public MongoTemplate warnMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(warnConfiguration));
    }
}