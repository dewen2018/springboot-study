package com.dewen.service;

import com.dewen.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    String saveCity(City city);

    List<City> findByName(String name);

    Page<City> findByPage(Pageable page);


    City findById(String id);

    List<City> findByCitynameOrProvinceid(String cityname, Long provinceid);

    Long deleteByCityname(String cityName);

    Long deleteById(String id);

    // List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);

    void testMethod();
}