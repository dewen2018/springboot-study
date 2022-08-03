package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.NearbyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NearbyUserMapper extends BaseMapper<NearbyUser> {
    @Select("select * from nearby_user where (longitude BETWEEN ${minlng} AND ${maxlng}) and (latitude BETWEEN ${minlat} AND ${maxlat})")
    List<NearbyUser> selectUser(@Param("minlng") double minX, @Param("maxlng") double maxX, @Param("minlat") double minY, @Param("maxlat") double maxY);
}
