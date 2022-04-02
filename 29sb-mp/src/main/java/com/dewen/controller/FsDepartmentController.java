package com.dewen.controller;


import com.dewen.service.IFsDepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 部门信息 前端控制器
 *
 * @author dj
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/fs-department")
public class FsDepartmentController {
    @Resource
    private IFsDepartmentService fsDepartmentServiceImpl;

    @GetMapping("/synFsDepartment")
    public void synFsDepartment() {

    }

    @GetMapping("/pageTest")
    public void pageTest() throws ParseException {
//        fsDepartmentServiceImpl.getTest();
        fsDepartmentServiceImpl.selectpage();
    }

}
