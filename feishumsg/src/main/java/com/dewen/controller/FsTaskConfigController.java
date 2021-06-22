package com.dewen.controller;


import com.dewen.service.IFsTaskConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 任务配置 前端控制器
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/fs-task-config")
public class FsTaskConfigController {

    @Resource
    private IFsTaskConfigService fsTaskConfigServiceImpl;

    @GetMapping("/synFsTaskConfig")
    public void synFsTaskConfig() {
        fsTaskConfigServiceImpl.synFsTaskConfig();
    }

}
