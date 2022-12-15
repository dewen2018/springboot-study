package com.dewen.controller;

import com.dewen.entity.Person;
import com.dewen.retrefitinterface.HttpApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private HttpApi httpApi;

    @GetMapping("/test")
    public Person test() {
        return httpApi.getPersonById(1);
    }
}
