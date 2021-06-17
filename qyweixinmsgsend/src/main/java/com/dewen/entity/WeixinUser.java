package com.dewen.entity;

import java.util.List;

public class WeixinUser {
    private String userid;
    private String name;
    private List<Integer> department;
    private String open_userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public String getOpen_userid() {
        return open_userid;
    }

    public void setOpen_userid(String open_userid) {
        this.open_userid = open_userid;
    }

    @Override
    public String toString() {
        return "WeixinUser{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", open_userid='" + open_userid + '\'' +
                '}';
    }
}
