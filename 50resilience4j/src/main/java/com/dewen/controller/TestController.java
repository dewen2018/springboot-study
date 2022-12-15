package com.dewen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "TestController")
public class TestController {

    @ApiOperation(value = "hello")
    @GetMapping("/hello")
    public String test() {
        return "Dewen's demo...";
    }
}
