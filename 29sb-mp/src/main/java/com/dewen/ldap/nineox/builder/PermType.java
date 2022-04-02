//package com.dewen.ldap.nineox.builder;
//
//import com.dewen.ldap.beans.Option;
//import com.dewen.ldap.lang.inum.Snum;
//
//@Option
//public enum PermType implements Snum {
//
//    myorg("myorg", "本机构"), myorgup("myorgup", "本上机构"), myorgsub("myorgsub", "本下机构"), mydep("mydep", "本部门"), mydepup("mydepup", "本上部门"), mydepsub("mydepsub", "本下部门"), myself("myself", "本人")
//    ,oxuserorg("oxuserorg", "关系表数据");
//
//    private String key;
//
//    private String desc;
//
//    private PermType(String key, String desc) {
//        this.key = key;
//        this.desc = desc;
//    }
//
//    @Override
//    public String getKey() {
//        return key;
//    }
//
//    @Override
//    public String getDesc() {
//        return desc;
//    }
//}