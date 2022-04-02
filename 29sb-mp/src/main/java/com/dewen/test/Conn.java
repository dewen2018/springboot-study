package com.dewen.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conn {
    String driver = "com.mysql.cj.jdbc.Driver";
    String dbName = "sqt_jxc_bs";
    String userName = "mysqldatabackup";
    String passwrod = "9ox654321";
    String url = "jdbc:mysql://192.168.9.19:3306/" + dbName + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true";
    String sql = "select * from pf_om_user";
    ResultSet rs;

    public ResultSet MyConn() {
        try {
            //加载驱动
            Class.forName(driver);
            //获取链接
            Connection conn = DriverManager.getConnection(url, userName, passwrod);
            //执行sql语句，存放结果集
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}