package com.dewen.LogAspect.repository;

import com.dewen.LogAspect.model.SystemLog;
import org.springframework.data.repository.CrudRepository;

public interface SystemLogRepository extends CrudRepository<SystemLog,Long> {

}