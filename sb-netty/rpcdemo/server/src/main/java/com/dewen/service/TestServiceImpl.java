package com.dewen.service;

import com.dewen.anno.RpcServer;
import com.dewen.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RpcServer
public class TestServiceImpl implements Test1Api {
    @Override
    public void test() {
        log.info("test method");
    }

    @Override
    public void test(int id, String name) {
        log.info("test,{},{}", id, name);
    }

    @Override
    public String testStr(int id) {
        log.info("testStr,{}", id);
        return "testStr " + id;
    }

    @Override
    public Object testObj() {
        log.info("testObj");
        Account account = new Account();
        account.setName("张三");
        return account;
    }
}
