package com.dewen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基础配置类
 */
@ConfigurationProperties(prefix = "topinfo.mongodb.base")
@Component
public class MongoBaseConfiguration extends MongoConfiguration {
 
    

}