package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.dewen.entity.NearbyUser;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class BaseRedisGeoHash {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //GEO相关命令用到的KEY
    private final static String KEY = "user_info";

    /**
     * @return
     */
    @GetMapping("/save")
    // public boolean save(NearbyUser user) {
    public boolean save() {
        NearbyUser user = new NearbyUser();
        user.setName("Dewen");
        user.setLongitude(113.03);
        user.setLatitude(23.36);

        Long flag = redisTemplate.opsForGeo()
                .add(KEY, new RedisGeoCommands.GeoLocation<>(
                        user.getName(),
                        new Point(user.getLongitude(), user.getLatitude()))
                );

        // redisTemplate.opsForGeo()
        //         .add(KEY, new RedisGeoCommands.GeoLocation<>(
        //                 user.getName() + "2",
        //                 new Point(113.02, 23.37))
        //         );

        return flag != null && flag > 0;
    }

    /**
     * http://localhost:8082/node2/redis/nearBySearch?distance=500&userLng=113.03&userLat=23.36
     * 根据当前位置获取附近指定范围内的用户
     *
     * @param distance 指定范围 单位km ，可根据{@link org.springframework.data.geo.Metrics} 进行设置
     * @param userLng  用户经度
     * @param userLat  用户纬度
     * @return
     */
    @GetMapping("/nearBySearch")
    public String nearBySearch(double distance, double userLng, double userLat) {
        List<NearbyUser> users = new ArrayList<>();
        // 1.GEORADIUS获取附近范围内的信息
        GeoResults<RedisGeoCommands.GeoLocation<Object>> reslut = redisTemplate.opsForGeo().radius(KEY,
                new Circle(new Point(userLng, userLat), new Distance(distance, Metrics.KILOMETERS)),
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                        .includeDistance()
                        .includeCoordinates()
                        .sortAscending());
        //2.收集信息，存入list
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> content = reslut.getContent();
        //3.过滤掉超过距离的数据
        content.stream().filter(a -> getDistance1(a.getContent().getPoint().getX(), a.getContent().getPoint().getY(), userLng, userLat) <= distance)
                .forEach(a -> users.add(
                        new NearbyUser(a.getDistance().getValue(), a.getContent().getPoint().getX(), a.getContent().getPoint().getY())
                ));
        return JSON.toJSONString(users);
    }

    private SpatialContext spatialContext = SpatialContext.GEO;


    /***
     * 球面中，两点间的距离（第三方库方法）
     * @param longitude 经度1
     * @param latitude  纬度1
     * @param userLng   经度2
     * @param userLat   纬度2
     * @return 返回距离，单位km
     */
    private double getDistance1(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }
}
