package com.dewen.model.dao.first;

import com.dewen.model.vo.People;

import java.util.List;
import java.util.Map;


public interface FirstDao {
    public Map<String, Object> firstDataAccess();

    public List<People> getListPeople(Map<String, Object> param);
}
