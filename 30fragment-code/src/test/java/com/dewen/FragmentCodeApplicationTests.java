package com.dewen;

import com.dewen.策略模式2.HandlerContext;
import com.dewen.策略模式2.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FragmentCodeApplicationTests {

    @Autowired
    private HandlerContext handlerContext;

    @Test
    void contextLoads() {
    }

    @Test
    public void studentServiceTest() {
        assert "HighSchoolStudent".equals(handlerContext.getInstance(StudentService.class, "HighSchool").handler());
        assert "UniversityStudent".equals(handlerContext.getInstance(StudentService.class, "University").handler());
    }

//    @Test
//    public void schoolServiceTest() {
//        assert "ChinaSchool".equals(handlerContext.getInstance(SchoolService.class, "").handler());
//    }
}
