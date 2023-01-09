package com.dewen.mapper;

import com.dewen.entity.EcUser;

import java.util.List;

public interface EcUserDao {
    List<EcUser> getAllUser();

    EcUser getUserByAccount(String userAccount);

    Boolean authUser(String userAccount, String password);
}