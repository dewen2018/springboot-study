package com.dewen.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Api(tags = "HelloController")
@ApiSupport(order = 1)
public class HelloController {

    @ApiOperation(value = "hello")
    @ApiOperationSupport(order = 1)
    @GetMapping("/hello")
    public String test() {
        return "Dewen's demo...";
    }
}
