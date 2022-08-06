package com.dewen.repository;

import com.dewen.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends ElasticsearchRepository<City, Long> {
    Long deleteByCityname(String cityName);

    // 根据cityname和省份id
    List<City> findByCitynameOrProvinceid(String cityname, Long provinceid);

    // 根据cityname查询
    List<City> findByCitynameIgnoreCase(String cityname);

    City queryCityById(String id);

    Long deleteById(String id);
}