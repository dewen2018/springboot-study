package com.dewen.controller;

import com.dewen.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @Title： demo
 * @Description： TODO
 * @Author： dewen
 * @Date： 2019/7/5 15:48
 */
@RestController
public class UserController {

    /**
     * 新增用户【描】
     *
     * @param user 用户实体类
     */
    @PostMapping("/add")
    public void addUser(User user) {
        System.out.println("新增一个用户");

    }

    /**
     * 查询一个用户【描述】
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/getUser")
    public User getUser(@RequestParam int id) {
        User user = new User();
        user.setId(id);
        user.setAccount("admin");
        user.setPassword("123456");
        user.setAge(18);
        return user;
    }

}
