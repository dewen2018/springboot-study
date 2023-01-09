package com.dewen.controller;

import com.dewen.entity.Person;
import com.dewen.mapper.EcUserDao;
import com.dewen.mapper.PersonRepository;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EcUserDao ecUserDao;

    @ApiOperation(value = "findAll")
    @ApiOperationSupport(order = 2)
    @GetMapping("/findAll")
    public void findAll() {

        // personRepository.findAll().forEach(p -> {
        //     System.out.println(p);
        // });
        ecUserDao.getAllUser();
    }

    @ApiOperation(value = "save")
    @ApiOperationSupport(order = 3)
    @GetMapping("/save")
    public void save() {
        Person person = new Person();
        person.setUid("uid:123");
        person.setUserName("dewen");
        person.setCommonName("du");
        person.setUserPassword("123456");
        personRepository.save(person);
    }
}
