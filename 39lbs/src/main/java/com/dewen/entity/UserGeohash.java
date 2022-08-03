package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "nearby_user_geohash")
public class UserGeohash extends NearbyUser {

    private String geoCode;
}
