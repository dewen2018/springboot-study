package com.baidu;

import com.baidu.fsg.uid.UidGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
//@MapperScan("com.baidu.fsg.uid")
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


    @Resource(name = "cachedUidGenerator")
//    @Resource(name = "defaultUidGenerator")
    private UidGenerator uidGenerator;

    @GetMapping("/getUid")
    public long getUid() {
        return uidGenerator.getUID();
    }
}
