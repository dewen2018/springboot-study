package com.dewen.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

// @Entry(base = "ou=people,dc=dewen,dc=com", objectClasses = "inetOrgPerson")
@Entry(base = "ou=people,dc=dewen,dc=com", objectClasses = {
        "top", "inetOrgPerson", "person", "organizationalPerson",
        "simpleSecurityObject"
})
@Data
public class Person {

    @Id
    private Name id;
    @DnAttribute(value = "uid", index = 3)
    private String uid;
    @Attribute(name = "cn")
    private String commonName;
    @Attribute(name = "sn")
    private String userName;
    private String userPassword;

}