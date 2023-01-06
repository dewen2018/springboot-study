package com.dewen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dewen.entity.User;
import com.dewen.mapper.UserMapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "UserController")
@ApiSupport(order = 2)
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "countByEmail")
    @ApiOperationSupport(order = 1)
    @GetMapping("/countByEmail")
    public long countByEmail() {
        return userMapper.countByEmail("863572313@qq.com");
    }

    @ApiOperation(value = "listAll")
    @ApiOperationSupport(order = 2)
    @GetMapping("/listAll")
    public List<User> listAll() {
        List<User> userList = userMapper.selectList(new QueryWrapper<>());
        return userList;
    }

    @ApiOperation(value = "add")
    @ApiOperationSupport(order = 3)
    @GetMapping("/add")
    public User add() {
        User user = new User();
        user.setEmail("863572313@qq.com");
        user.setCreateTime(new Date());

        user.setPassword("123456");
        user.setMobile("110");
        userMapper.insert(user);
        return user;
    }
}
