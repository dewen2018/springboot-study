package com.dewen.mybatisplus.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","Hello , SpringBoot!!!");
        mv.setViewName("index");
        return mv;
    }
}