package com.dewen.dao;

import com.dewen.entity.UserBean;

import java.util.List;

public interface TestDao {

    void saveUser(UserBean user);

    void saveUserList(List<UserBean> userList);

    void updateUser(UserBean user);

    void deleteUserById(Long id);
}
