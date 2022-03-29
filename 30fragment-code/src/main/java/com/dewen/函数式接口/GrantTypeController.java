package com.dewen.函数式接口;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrantTypeController {

    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @GetMapping("/grantType")
    public String test(String resourceName) {
        return queryGrantTypeService.getResult(resourceName);
    }
}