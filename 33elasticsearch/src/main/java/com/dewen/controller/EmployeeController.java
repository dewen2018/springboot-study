package com.dewen.controller;

import com.alibaba.fastjson.JSONObject;
import com.dewen.entity.Employee;
import com.dewen.repository.EmployeeRepository;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Api(tags = "employee index")
@ApiSort(1)
public class EmployeeController {

    @Autowired
    private EmployeeRepository er;

    @ApiOperation(value = "增加", notes = "增加...", httpMethod = "GET")
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("dewen");
        employee.setLastName("du");
        employee.setAge(18);
        employee.setAbout("杜少杰，杜du少杰");
        er.save(employee);
        System.err.println("add a obj");
        return "success";
    }

    @ApiOperation(value = "局部更新", notes = "局部更新...", httpMethod = "GET")
    @GetMapping("/update")
    public String update() {
        Employee employee = er.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        er.save(employee);
        System.err.println("update a obj");
        return "success";
    }

    @ApiOperation(value = "查询", notes = "查询...", httpMethod = "GET")
    @GetMapping("/query/{id}")
    public Employee query(@PathVariable("id") String id) {
        Employee accountInfo = er.queryEmployeeById(id);
        System.err.println(JSONObject.toJSONString(accountInfo));
        return accountInfo;
    }

    @ApiOperation(value = "删除", notes = "删除...", httpMethod = "GET")
    @GetMapping("/delete")
    public String delete() {
        Employee employee = new Employee();
        employee.setId("1");
        er.delete(employee);
        return "success";
    }
}