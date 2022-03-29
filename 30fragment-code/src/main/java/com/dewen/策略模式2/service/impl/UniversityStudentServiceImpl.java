package com.dewen.策略模式2.service.impl;

import com.dewen.策略模式2.HandlerType;
import com.dewen.策略模式2.service.StudentService;
import org.springframework.stereotype.Component;

@Component
@HandlerType(type = StudentService.class, value = "University")
public class UniversityStudentServiceImpl implements StudentService {
    @Override
    public String handler() {
        return "UniversityStudent";
    }
}
