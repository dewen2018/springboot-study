package com.dewen.LogAspect.service.impl;

import com.dewen.LogAspect.annotation.OperationLogDetail;
import com.dewen.LogAspect.enums.OperationType;
import com.dewen.LogAspect.enums.OperationUnit;
import com.dewen.LogAspect.model.User;
import com.dewen.LogAspect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements com.dewen.LogAspect.service.impl.UserService {

    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    @Override
    public String findUserName(String tel) {
        System.out.println("tel:" + tel);
        return "zhangsan";
    }





    @Autowired
    UserRepository userRepository ;

    @Override
    public List<User> findAll() {
        return new ArrayList<>();
//        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }
}