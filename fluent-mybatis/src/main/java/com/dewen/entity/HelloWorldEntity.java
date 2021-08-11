package com.dewen.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;

import java.util.Date;

@FluentMybatis
public class HelloWorldEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String sayHello;

    private String yourName;

    private Date gmtCreated;

    private Date gmtModified;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSayHello() {
        return sayHello;
    }

    public void setSayHello(String sayHello) {
        this.sayHello = sayHello;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "HelloWorldEntity{" +
                "id=" + id +
                ", sayHello='" + sayHello + '\'' +
                ", yourName='" + yourName + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                ", isDeleted=" + isDeleted +
                '}';
    }
}