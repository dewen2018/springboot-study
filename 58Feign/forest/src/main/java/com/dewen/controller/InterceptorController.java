package com.dewen.controller;

import com.dewen.client2.TestInterceptorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InterceptorController {

    private static Logger logger = LoggerFactory.getLogger(InterceptorController.class);

    @Autowired
    private TestInterceptorClient testInterceptorClient;

    @PostMapping("/receive-interceptor")
    public String receiveInterceptor(HttpServletRequest request, @RequestParam String username) {
        String token = request.getHeader("accessToken");
        logger.info("accessToken: {}", token);
        return "ok";
    }

    @GetMapping("/test-interceptor")
    public String testInterceptor(@RequestParam String username) {
        String result = testInterceptorClient.testInterceptor(username);
        return result;
    }

}