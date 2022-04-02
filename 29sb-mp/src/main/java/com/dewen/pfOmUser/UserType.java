package com.dewen.pfOmUser;

import com.dewen.ldap.lang.inum.Snum;

public enum UserType implements Snum {

    ADMIN("admin", "系统管理员"), DEPLOYADMIN("deployAdmin", "后台管理员"), DEPLOY("deploy", "后台人员"), CUSTOMERADMIN("customerAdmin", "客户管理员"), CUSTOMER("customer", "客户员工"),
//	SUPERVISE("supervise", "监理")
    ;

    private String key;

    private String desc;

    private UserType(String key, String desc) {
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
