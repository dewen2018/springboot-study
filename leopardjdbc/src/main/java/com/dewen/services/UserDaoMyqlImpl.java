package com.dewen.services;

import com.dewen.entity.*;
import io.leopard.jdbc.Jdbc;
import io.leopard.jdbc.builder.InsertBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoMyqlImpl {

    @Resource
    private Jdbc jdbc;

    public boolean add() {
        User user = new User();
        user.setId(111);
        user.setPassword("1111");
        InsertBuilder builder = new InsertBuilder("user");
        builder.setLong("id", user.getId());
        builder.setString("password", user.getPassword());
        return this.jdbc.insertForBoolean(builder);
    }

//    public User get(long uid) {
//        String sql = "select * from user where uid=?";
//        return this.jdbc.query(sql, User.class);
//    }
//
//    public boolean delete(long uid) {
//        String sql = "delete from user where uid=?";
//        return this.jdbc.updateForBoolean(sql, uid);
//    }
}