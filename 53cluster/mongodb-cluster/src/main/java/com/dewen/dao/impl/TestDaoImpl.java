package com.dewen.dao.impl;

import com.alibaba.fastjson.JSON;
import com.dewen.dao.TestDao;
import com.dewen.entity.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDaoImpl implements TestDao {

    @Autowired
    @Qualifier("baseMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(UserBean user) {
        String userJson = JSON.toJSONString(user);
        mongoTemplate.save(userJson, "tx");
    }

    @Override
    public void saveUserList(List<UserBean> userList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUser(UserBean user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUserById(Long id) {
        // TODO Auto-generated method stub

    }

}