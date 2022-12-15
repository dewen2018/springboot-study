package com.dewen.controller;

import com.dewen.utils.LocaleMessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @RequestMapping("/hello")
    public String hello() {
        String welcome = messageSourceUtil.getMessage("welcome");
        System.out.println(welcome);
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        String welcome = messageSourceUtil.getMessage("welcome");
        System.out.println(welcome);
        return "login";
    }
}
