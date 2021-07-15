package com.dewen.mybatisplus.modules.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.mybatisplus.modules.entity.User;
import com.dewen.mybatisplus.modules.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
