package com.dewen;

import com.dewen.service.IFsDepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeishumsgApplicationTests {

    @Autowired
    private IFsDepartmentService fsDepartmentServiceImpl;


    @Test
    void contextLoads() {
        fsDepartmentServiceImpl.pageTest();
    }

}
