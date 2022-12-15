package com.dewen.dao;


import com.dewen.model.User;

public interface IUserDao {
    User selectUser(long id);
}