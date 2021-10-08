package com.dewen.service.impl;

import com.dewen.entity.Tdict;
import com.dewen.mapper.TdictMapper;
import com.dewen.service.TdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdictServiceImpl implements TdictService {

    @Autowired
    private TdictMapper mapper;

    @Override
    public int insertTdict(Tdict entity) {
        return mapper.insert(entity);
    }

    @Override
    public Tdict getById(Integer id) {
        return mapper.selectById(id);
    }
}