package com.dewen.controller;

import com.dewen.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InterConterller {

    @GetMapping("/getPersonById")
    public Person getPersonById(@RequestParam Integer id, HttpServletRequest httpServletRequest) {
        //headers
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        for (Enumeration e = headerNames; e.hasMoreElements(); ) {
            String thisName = e.nextElement().toString();
            String thisValue = httpServletRequest.getHeader(thisName);
            System.out.println(thisName + "--------------" + thisValue);
        }
        //parameter
        Map<String, String[]> param = httpServletRequest.getParameterMap();
        param.forEach((k, v) -> {
            System.out.println("key:value = " + k + ":" + v[0]);
        });

        if (id == 1) {
            return new Person(1, "dewen", 18, "珠海");
        } else {
            return new Person();
        }
    }
}
