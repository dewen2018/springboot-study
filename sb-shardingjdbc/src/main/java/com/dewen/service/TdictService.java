package com.dewen.service;

import com.dewen.entity.Tdict;

public interface TdictService {

    int insertTdict(Tdict entity);

    Tdict getById(Integer id);
}
