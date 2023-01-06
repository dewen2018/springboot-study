package com.dewen.controller;

import com.dewen.entity.DewenUser;
import com.dewen.mapper.DewenUserMapper;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dewenUser")
@Api(tags = "DewenUserController")
@ApiSupport(order = 3)
public class DewenUserController {

    private final DewenUserMapper dewenUserMapper;

    public DewenUserController(DewenUserMapper dewenUserMapper) {
        this.dewenUserMapper = dewenUserMapper;
    }


    @GetMapping("/getById")
    public DewenUser get(Integer id) {
        return dewenUserMapper.selectByPrimaryKey(id);
    }


    @GetMapping("/findAllUser")
    public List<DewenUser> findAllUser() {
        return dewenUserMapper.findAllUser();
    }
}
