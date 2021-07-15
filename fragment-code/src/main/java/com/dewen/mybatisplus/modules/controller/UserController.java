package com.dewen.mybatisplus.modules.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dewen.mybatisplus.core.util.BuildConditionWrapper;
import com.dewen.mybatisplus.core.vo.Conditions;
import com.dewen.mybatisplus.modules.entity.User;
import com.dewen.mybatisplus.modules.mapper.UserMapper;
import com.dewen.mybatisplus.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:liangjl
 * @Date:2020-08-16
 * @Description:用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 添加
     */
    @RequestMapping("/add")
    public void add() {
        userMapper.insert(User.builder().userName("周伯通").passWord("123456").build());
    }

    /**
     * 修改
     * @param user
     */
    @PutMapping("/updateById")
    public void updateById(@RequestBody User user) {
        userMapper.updateById(user);
    }
    /**
     * 删除通过多个主键Id进行删除
     * @param ids
     */
    @DeleteMapping("/deleteByIds")
    public void deleteByIds(@RequestBody List<String> ids) {
        userMapper.deleteBatchIds(ids);
    }

    /**
     * 通过指定Id进行查询
     *
     * @param userId
     */
    @GetMapping("/getOne/{userId}")
    public void getOne(@PathVariable("userId") Long userId) {
        User user = userMapper.selectById(userId);
        System.out.println(JSON.toJSON(user));

    }

    /**
     * 用户分页，参数有多个使用下标索引进行处理.如果有两个参数(如用户名和地址)：conditionList[0].fieldName=userName、 conditionList[0].fieldName=address
     * 未转码请求分页地址: http://localhost:7011/user/page?conditionList[0].fieldName=userName&conditionList[0].operation=LIKE&conditionList[0].value=周
     * 已转码请求分页地址: http://localhost:7011/user/page?conditionList[0].fieldName=userName&conditionList[0].operation=LIKE&conditionList[0].value=%E5%91%A8
     * @param page
     * @param conditions 条件
     * @return
     */
    @GetMapping("/page")
    public IPage<User> page(Page page, Conditions conditions) {
        QueryWrapper<User> build = BuildConditionWrapper.build(conditions.getConditionList(), User.class);
        build.lambda().orderByDesc(User::getCreateTime);
        return userService.page(page, build);
    }


}
