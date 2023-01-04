package com.dewen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class ConsumerController {
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("socket");
        mav.addObject("uid", UUID.randomUUID());
        return mav;
    }

    @RequestMapping("/bulletScreen")
    public ModelAndView bulletScreen() {
        ModelAndView mav = new ModelAndView("bulletScreen");
        mav.addObject("uid", UUID.randomUUID());
        return mav;
    }
}
