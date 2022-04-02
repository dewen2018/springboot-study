package com.dewen.pfOmUser;


import com.dewen.ldap.lang.inum.Snum;

public enum UserStatus implements Snum {

    INIT("init", "未启用"), DELETED("deleted", "已删除"), ENABLE("enable", "已启用"), DISABLE("disable", "已禁用");

    private String key;

    private String desc;

    private UserStatus(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
