package com.dewen.controller;

import com.dewen.annotations.ResponseOriginalData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hanler")
public class TestController {
    @ResponseOriginalData
    @GetMapping("/sb")
    public String sb() throws Exception {
        if (true)
            throw new Exception("mmp");
        return "edge";
    }
}
