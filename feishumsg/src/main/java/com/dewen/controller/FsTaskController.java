package com.dewen.controller;


import com.dewen.service.IFsTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/fs-task")
public class FsTaskController {

    @Resource
    private IFsTaskService fsTaskServiceImpl;

    @GetMapping("/synFsTask")
    public void synFsTask() {
        fsTaskServiceImpl.synFsTask();
    }
}
