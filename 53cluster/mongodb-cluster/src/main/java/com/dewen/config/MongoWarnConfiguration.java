package com.dewen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mongo预警配置类
 */
@ConfigurationProperties(prefix = "topinfo.mongodb.warn")
@Component
public class MongoWarnConfiguration extends MongoConfiguration {
 
     
}