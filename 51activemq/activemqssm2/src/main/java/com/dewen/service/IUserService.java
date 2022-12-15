package com.dewen.service;


import com.dewen.model.User;

public interface IUserService {
    User selectUser(long userId);
}