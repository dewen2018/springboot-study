package com.dewen;

import com.dewen.call.AsynTask;
import com.dewen.call.SynTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Future;

@SpringBootTest
class ProjectnameApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private SynTask synTask;

    @Test
    public void test() throws Exception {
        synTask.doTaskOne();
        synTask.doTaskTwo();
        synTask.doTaskThree();
    }


    @Autowired
    private AsynTask asynTask;

    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = asynTask.doTaskOne();
        Future<String> task2 = asynTask.doTaskTwo();
        Future<String> task3 = asynTask.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(500);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}
