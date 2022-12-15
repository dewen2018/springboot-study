package com.dewen.LogAspect.service;

import com.dewen.LogAspect.model.SystemLog;

import java.util.List;

public interface SystemLogService {

    List<SystemLog> findAll();

    void saveUser(SystemLog log);

    SystemLog findOne(long id);

    void delete(long id);
}