package com.dewen.controller;


import com.dewen.service.IFsTaskTypeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 飞书任务类型 前端控制器
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/fs-task-type-config")
public class FsTaskTypeConfigController {
    @Autowired
    private IFsTaskTypeConfigService fsTaskTypeConfigServiceImpl;

    @GetMapping("/synConfigInfo")
    public void synConfigInfo() {
        fsTaskTypeConfigServiceImpl.synConfigInfo();
    }

}
