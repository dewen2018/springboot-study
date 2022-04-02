package com.dewen;

import com.dewen.service.IFsDepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class FeishumsgApplicationTests {

    @Autowired
    private IFsDepartmentService fsDepartmentServiceImpl;


    @Test
    void contextLoads() throws ParseException {
//        fsDepartmentServiceImpl.pageTest();
//        fsDepartmentServiceImpl.selectpage();
        fsDepartmentServiceImpl.getTest();
    }

}
