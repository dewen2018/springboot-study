package com.dewen.test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public static void main(String[] args) throws SQLException {
        Conn dsh = new Conn();
        ResultSet rs = dsh.MyConn();

        //共几条记录
        rs.last(); //移到最后一行   
        int rowCount = rs.getRow(); //得到当前行号，也就是记录数   
        rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置
        System.out.println("共" + rowCount + "条记录");

        //逐条输出
        while (rs.next()) {
//            System.out.println(rs.getString("name") + " " + rs.getString("account"));
            /*或者rs.getString(1)+" "+rs.getString(2)*/
        }

        //第一条是
        rs.first();//将光标移动到第一条
        System.out.println("第一条是：" + rs.getString("name") + " " + rs.getString("account"));

        //最后一条是
        rs.last();//将光标移动到第一条
        System.out.println("最后一条是：" + rs.getString("name") + " " + rs.getString("account"));
    }

}