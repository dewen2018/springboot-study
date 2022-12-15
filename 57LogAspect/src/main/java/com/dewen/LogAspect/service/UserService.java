package com.dewen.LogAspect.service.impl;

import com.dewen.LogAspect.model.User;

import java.util.List;

public interface UserService {

    /**
     * 获取用户信息
     * @return
     * @param tel
     */
    String findUserName(String tel);

    /**
     * second
     */
    List<User> findAll();

    void saveUser(User book);

    User findOne(long id);

    void delete(long id);

    List<User> findByName(String name);
}