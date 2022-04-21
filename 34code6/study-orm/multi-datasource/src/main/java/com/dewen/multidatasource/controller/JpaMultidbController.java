package com.dewen.multidatasource.controller;

import com.dewen.multidatasource.entity.master.StudentDao;
import com.dewen.multidatasource.entity.slave.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaMultidbController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping("/list")
    public void list() {
        System.out.println(studentDao.findAll());
        System.out.println(teacherDao.findAll());
    }
}
