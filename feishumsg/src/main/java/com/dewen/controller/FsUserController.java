package com.dewen.controller;


import com.dewen.service.IFsDepartmentService;
import com.dewen.service.IFsUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/fs-user")
public class FsUserController {
    @Resource
    private IFsUserService fsUserServiceImpl;

    @GetMapping("/synFsUser")
    public void synFsUser(){
        fsUserServiceImpl.synFsUser();
    }
}
