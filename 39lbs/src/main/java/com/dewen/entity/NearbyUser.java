package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NearbyUser {
    private Integer id;
    private String name;
    private Double longitude;
    private Double latitude;
    private LocalDateTime createTime;
    @TableField(exist = false)
    private Double Distance;

    public NearbyUser(Double distance, Double latitude, Double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        Distance = distance;
    }

    public NearbyUser() {
    }
}
