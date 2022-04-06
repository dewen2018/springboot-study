package com.improvedthroughput.m1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private HelloService hello;

    @GetMapping("/helloworld")
    public String helloWorldController() throws InterruptedException {
        return hello.sayHello();
    }

    /**
     * 异步调用restful
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     * @return
     */
    @GetMapping("/hello")
    public Callable<String> helloController() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " 进入call方法");
                String say = hello.sayHello();
                logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;
            }
        };
        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;
    }
}