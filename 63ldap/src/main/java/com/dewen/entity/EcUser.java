package com.dewen.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.ldap.LdapName;

@Data
@Entry(objectClasses = {"user"}, base = "ou=Domain Users")
public class EcUser {

    @Id
    private LdapName ldapName;
    /**
     * 域账号
     */
    @Attribute(name = "sAMAccountName")
    private String userAccount;

    /**
     * 姓名
     */
    @Attribute(name = "displayName")
    private String userName;

    /**
     * 邮箱
     */
    @Attribute(name = "mail")
    private String userEmail;

    /**
     * 部门
     */
    @Attribute(name = "department")
    private String department;

    /**
     * 岗位
     */
    @Attribute(name = "title")
    private String title;

    @Attribute(name = "memberOf")
    private String groupListStr;
}