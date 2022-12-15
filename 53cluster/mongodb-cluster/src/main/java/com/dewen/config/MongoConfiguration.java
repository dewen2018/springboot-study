package com.dewen.config;

/**
 * @Description: mongo配置类 - 父类
 */
public class MongoConfiguration {

    private String uri;

    private String database;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}