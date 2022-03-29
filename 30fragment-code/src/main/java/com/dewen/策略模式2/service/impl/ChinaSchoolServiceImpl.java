package com.dewen.策略模式2.service.impl;

import com.dewen.策略模式2.HandlerType;
import com.dewen.策略模式2.service.SchoolService;
import org.springframework.stereotype.Component;

@Component
@HandlerType(type = SchoolService.class)
public class ChinaSchoolServiceImpl implements SchoolService {
    @Override
    public String handler() {
        return "ChinaSchool";
    }
}
