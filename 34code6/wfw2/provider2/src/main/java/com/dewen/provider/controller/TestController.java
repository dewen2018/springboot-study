package com.dewen.provider.controller;

import com.dewen.provider.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private TestClient testClient;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        try {
            return testClient.getReturn(name);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
