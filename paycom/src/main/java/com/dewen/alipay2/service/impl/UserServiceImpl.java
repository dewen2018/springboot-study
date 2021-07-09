package com.dewen.alipay2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.alipay2.mapper.UserMapper;
import com.dewen.alipay2.pojo.User;
import com.dewen.alipay2.pojo.UserExample;
import com.dewen.alipay2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User getUserById(String userId) {

        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getUserList() {

        UserExample ue = new UserExample();
        List<User> userList = userMapper.selectByExample(ue);

        return userList;
    }

}
