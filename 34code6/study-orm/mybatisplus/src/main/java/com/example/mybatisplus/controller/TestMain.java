package com.example.mybatisplus.controller;

import com.example.mybatisplus.entity.Student;
import com.example.mybatisplus.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mybatisplus")
public class TestMain {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/list")
    public List<Student> list(){
        List<Student> students = studentMapper.selectList(null);
        return students;
    }


    @GetMapping("/save")
    public String save(){
        Student student = new Student();
        student.setId(2);
        student.setCity("杭州");
        student.setName("马云");
        student.setSchool("杭州师范");
        studentMapper.insert(student);
        return "success";
    }

}
