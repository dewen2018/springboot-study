package com.dewen;

import com.dewen.service.UidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaiduuidgeneratorApplicationTests {

    @Autowired
    private UidGenerator uidGenerator;
    @Test
    void contextLoads() {
        long uid = uidGenerator.getUID();
        System.out.println("我是生成的uid:"+uid);
        System.out.println("我是parseUid:"+uidGenerator.parseUID(uid));
    }
}
