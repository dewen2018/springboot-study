package com.dewen.策略模式2.service.impl;

import com.dewen.策略模式2.HandlerType;
import com.dewen.策略模式2.service.StudentService;
import org.springframework.stereotype.Component;

@Component
@HandlerType(type = StudentService.class, value = "HighSchool")
public class HighSchoolStudentServiceImpl implements StudentService{
    @Override
    public String handler() {
        return "HighSchoolStudent";
    }
}
