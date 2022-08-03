package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.NearbyUser;
import com.dewen.entity.UserGeohash;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserGeohashMapper extends BaseMapper<UserGeohash> {
    @Select("select * from nearby_user_geohash where (longitude BETWEEN ${minlng} AND ${maxlng}) and (latitude BETWEEN ${minlat} AND ${maxlat})")
    List<NearbyUser> selectUser(@Param("minlng") double minX, @Param("maxlng") double maxX, @Param("minlat") double minY, @Param("maxlat") double maxY);
}
