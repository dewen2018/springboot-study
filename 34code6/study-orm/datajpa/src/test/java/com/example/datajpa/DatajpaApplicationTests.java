package com.example.datajpa;

import com.example.datajpa.entity.User;
import com.example.datajpa.service.primary.PrimaryRepository;
import com.example.datajpa.service.second.SecondRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatajpaApplicationTests {
    @Autowired
    private PrimaryRepository primaryRepository;
    @Autowired
    private SecondRepository secondRepository;

    @Test
    public void hello(){
//        System.out.println(primaryRepository.findAll().size());
//        System.out.println(secondRepository.findAll().size());
        User user = new User();
        user.setId(4L);
        user.setName("xiaoming");
        user.setAge(15);
        primaryRepository.save(user);
        secondRepository.save(user);
    }
    @Test
    public void contextLoads() {
    }

}

