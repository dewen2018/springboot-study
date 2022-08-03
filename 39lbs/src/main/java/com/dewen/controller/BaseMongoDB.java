package com.dewen.controller;

import com.alibaba.fastjson.JSONObject;
import com.dewen.entity.Hotel;
import com.dewen.repository.HotelRepository;
import org.springframework.data.domain.*;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo")
public class BaseMongoDB {

    @Resource
    HotelRepository hotelRepository;

    @Resource
    MongoTemplate mongoTemplate;


    @GetMapping("/adada")
    public void adada() {
        // Metrics.KILOMETERS加不加结果一样？？
        // NearQuery query = NearQuery.near(115.999567, 28.681813, Metrics.KILOMETERS)
        //         .spherical(true)
        //         .distanceMultiplier(6378137)
        //         // 2000米
        //         .maxDistance(2000);
        // Aggregation aggregation = Aggregation.newAggregation(
        //         Aggregation.geoNear(query, "distance")
        // );
        // // { "$geoNear" : { "maxDistance" : 3.135711885774796E-4, "distanceMultiplier" : 6378137.0, "near" : [115.999567, 28.681813], "spherical" : true, "distanceField" : "distance"}
        // AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "hotel", Map.class);
        //
        // List<Map> maps = aggregationResults.getMappedResults();
        // for (Map hotel : maps) {
        //     System.out.println(JSONObject.toJSONString(hotel));
        // }

        //查找附件500米的未使用的单车，要求只显示最近的10辆
        NearQuery nearQuery = NearQuery.near(115.999567, 28.681813, Metrics.KILOMETERS);
        nearQuery.maxDistance(0.2)
                .query(
                        new Query()
                                // .addCriteria(Criteria.where("status").is(0))
                                .limit(10)
                );
        // { "$geoNear" : { "query" : {}, "maxDistance" : 3.135711885774796E-5, "distanceMultiplier" : 6378.137, "near" : [115.999567, 28.681813], "spherical" : true, "distanceField" : "dis"}}, { "$limit" : 10}
        GeoResults<Hotel> hotelGeoResults = mongoTemplate.geoNear(nearQuery, Hotel.class);
        for (GeoResult<Hotel> hotelGeoResult : hotelGeoResults) {
            System.out.println(JSONObject.toJSONString(hotelGeoResult));
        }
    }


    @GetMapping("/listHotel")
    private List<Hotel> listHotel() {
        return hotelRepository.findAll();
    }

    @GetMapping("/getAllByName")
    private List<Hotel> getAllByName() {
        return hotelRepository.getAllByName("hotel1");
    }

    @GetMapping("/saveHotel")
    public Hotel saveHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("dewen's hotel");
        double[] location = {116.02477, 28.68667};
        hotel.setLocation(location);
        return hotelRepository.save(hotel);
    }

    @GetMapping("/deleteById")
    public void deleteById() {
        hotelRepository.deleteById("62e7a8c406a451205240991b");
    }

    @GetMapping("/readHotelByName/{name}")
    private Optional<Hotel> readHotelByName(@PathVariable("name") String name) {
        Hotel hotel = new Hotel();
        hotel.setName("dewen's hotel");
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("location");
        Example<Hotel> example = Example.of(hotel, matcher);
        return hotelRepository.findOne(example);
    }

    /**
     * 根据一个或者多个属性分页查询
     * http://localhost:8082/node2/mongo/page/1/pagesize/10/name/dewen
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/page/{pageNumber}/pagesize/{pageSize}/name/{name}")
    public Page<Hotel> readUsersByPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("name") String name) {
        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageSize == 0) {
            pageSize = 20;
        }

        Hotel hotel = new Hotel();
        hotel.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("name");
        Example<Hotel> example = Example.of(hotel, matcher);


        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        Sort sort = Sort.by(orders);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return hotelRepository.findAll(example, pageable);
    }

    @GetMapping("/findByNameLike")
    public Page<Hotel> findByNameLike() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return hotelRepository.findByNameLike("hotel2", pageRequest);
    }
}
