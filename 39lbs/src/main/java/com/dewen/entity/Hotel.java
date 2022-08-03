package com.dewen.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "hotel")
public class Hotel {
    @Id
    private String id;
    private String name;
    //地理位置字段，里面保存着经纬度,这个字段建立索引，那么一查找就快了，可以将地理坐标使用GeoHash转换一个字符窜，然后进行查找
    //顺序=> 经度  维度
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    // private List<Double> location;
    private double[] location;

}
