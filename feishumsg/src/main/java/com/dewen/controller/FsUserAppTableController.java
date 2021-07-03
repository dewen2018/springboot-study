package com.dewen.controller;


import com.dewen.service.IFsUserAppTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户多维表格 前端控制器
 * </p>
 *
 * @author dj
 * @since 2021-06-29
 */
@RestController
@RequestMapping("/fs-user-app-table")
public class FsUserAppTableController {
    @Resource
    private IFsUserAppTableService fsUserAppTableServiceImpl;

    @GetMapping("/createFsUserAppTable")
    public void createFsUserAppTable() {
        // fsUserAppTableServiceImpl.createFsUserAppTable();
        fsUserAppTableServiceImpl.synTasks();
    }

}
