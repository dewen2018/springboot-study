package com.dewen.controller;

import com.dewen.repository.master.StudentDao;
import com.dewen.repository.slave.TeacherDao;
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
    public Object[] list() {
        Object[] obj =  new Object[2];
        obj[0] = studentDao.findAll();
        obj[1] =teacherDao.findAll();
        return  obj;
    }
}
