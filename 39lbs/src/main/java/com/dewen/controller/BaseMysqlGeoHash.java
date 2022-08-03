package com.dewen.controller;

import ch.hsr.geohash.GeoHash;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dewen.entity.UserGeohash;
import com.dewen.mapper.UserGeohashMapper;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 此种方式是纯基于mysql实现的，未使用GeoHash算法。
 */
@RestController
public class BaseMysqlGeoHash {
    private SpatialContext spatialContext = SpatialContext.GEO;

    @Resource
    UserGeohashMapper userGeohashMapper;


    /***
     * 添加用户
     * @return
     */
    // @PostMapping("/addUser")
    // public Integer add(@RequestBody UserGeohash user) {
    @GetMapping("/addUser")
    public Integer add() {
        UserGeohash user = new UserGeohash();
        //默认精度12位
        user.setName("dewen0801");
        user.setLongitude(110.0);
        user.setLatitude(21.86);

        String geoHashCode = GeohashUtils.encodeLatLon(user.getLatitude(), user.getLongitude());
        user.setGeoCode(geoHashCode);
        user.setCreateTime(LocalDateTime.now());
        return userGeohashMapper.insert(user);
    }

    /**
     * http://localhost:8082/node2/nearbyGeohash?distance=500&len=7&userLng=113.03&userLat=23.36
     * 获取附近指定范围的人
     *
     * @param distance 距离范围（附近多远的用户） 单位km
     * @param len      geoHash的精度（几位的字符串）
     * @param userLng  当前用户的经度
     * @param userLat  当前用户的纬度
     * @return json
     */
    @GetMapping("/nearbyGeohash")
    public String nearBySearch(@RequestParam("distance") double distance,
                               @RequestParam("len") int len,
                               @RequestParam("userLng") double userLng,
                               @RequestParam("userLat") double userLat) {
        //1.根据要求的范围，确定geoHash码的精度，获取到当前用户坐标的geoHash码
        GeoHash geoHash = GeoHash.withCharacterPrecision(userLat, userLng, len);
        //2.获取到用户周边8个方位的geoHash码
        GeoHash[] adjacent = geoHash.getAdjacent();
        QueryWrapper<UserGeohash> queryWrapper = new QueryWrapper<UserGeohash>().likeRight("geo_code", geoHash.toBase32());
        Stream.of(adjacent).forEach(a -> queryWrapper.or().likeRight("geo_code", a.toBase32()));

        //3.匹配指定精度的geoHash码
        List<UserGeohash> users = userGeohashMapper.selectList(queryWrapper);
        //4.过滤超出距离的
        users = users.stream()
                .filter(a -> getDistance1(a.getLongitude(), a.getLatitude(), userLng, userLat) <= distance)
                .collect(Collectors.toList());
        return JSON.toJSONString(users);
    }


    /***
     * 球面中，两点间的距离（第三方库方法）
     * @param longitude 经度1
     * @param latitude  纬度1
     * @param userLng   经度2
     * @param userLat   纬度2
     * @return 返回距离，单位km
     */
    private  double getDistance1(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }
}
