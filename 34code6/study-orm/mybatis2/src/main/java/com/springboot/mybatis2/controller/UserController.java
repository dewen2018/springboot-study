package com.springboot.mybatis2.controller;

import java.util.List;

import com.springboot.mybatis2.entity.UserEntity;
import com.springboot.mybatis2.mapper.test1.User1Mapper;
import com.springboot.mybatis2.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        List<UserEntity> users = user1Mapper.getAll();
        return users;
    }

//    @RequestMapping("/getUsers2")
//    public List<UserEntity> getUsers2() {
//        List<UserEntity> users=user2Mapper.getAll();
//        return users;
//    }

    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
        UserEntity user = user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        user2Mapper.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(UserEntity user) {
        user2Mapper.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }


}