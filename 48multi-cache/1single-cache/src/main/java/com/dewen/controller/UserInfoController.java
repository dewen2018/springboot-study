package com.dewen.controller;

import com.dewen.entity.UserInfo;
import com.dewen.service.IUserInfoService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "UserInfoController")
@ApiSupport(order = 1)

public class UserInfoController {
    @Resource
    private IUserInfoService userInfoService;


    @GetMapping("/{id}")
    public Object getUserInfo(@PathVariable Integer id) {
        UserInfo userInfo = userInfoService.getByName(id);
        if (userInfo == null) {
            return "没有该用户";
        }
        return userInfo;
    }

    @GetMapping("/v2/{id}")
    public Object getUserInfo2(@PathVariable Integer id) {
        UserInfo userInfo = userInfoService.getByName2(id);
        if (userInfo == null) {
            return "没有该用户";
        }
        return userInfo;
    }

    @PostMapping("/")
    public Object creatUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.addUserInfo(userInfo);
        return "SUCCESS";
    }

    @PutMapping("/")
    public Object updateUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo userInfo2 = userInfoService.updateUserInfo(userInfo);
        if (userInfo2 == null) {
            return "该用户不存在";
        }
        return userInfo2;
    }

    @DeleteMapping("/{id}")
    public Object deleteUserInfo(@PathVariable Integer id) {
        userInfoService.deleteById(id);
        return "SUCCESS";
    }
}