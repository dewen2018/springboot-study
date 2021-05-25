package com.dewen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/dewen"))
public class DewenController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello," + name;
    }
}
