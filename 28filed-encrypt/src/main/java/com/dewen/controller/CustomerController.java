package com.dewen.controller;

import com.dewen.entity.Customer;
import com.dewen.mappers.CustomerMapper;
import com.dewen.typehandler.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("addCustomer")
    public String addCustomer(@RequestParam("phone") String phone, @RequestParam("address") String address) {
        int result = customerMapper.addCustomer(new Encrypt(phone), address,2 );
        return "添加结果: " + result;
    }

    @GetMapping("findCustomer")
    public List<Customer> findCustomer(@RequestParam("phone") String phone) {
        return customerMapper.findCustomer(new Encrypt(phone));
    }
}
