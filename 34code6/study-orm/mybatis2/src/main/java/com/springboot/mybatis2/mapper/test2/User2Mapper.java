package com.springboot.mybatis2.mapper.test2;

import java.util.List;

import com.springboot.mybatis2.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface User2Mapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);

}