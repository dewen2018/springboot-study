package com.springboot.mybatis2.mapper.test1;


import com.springboot.mybatis2.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface User1Mapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);

}