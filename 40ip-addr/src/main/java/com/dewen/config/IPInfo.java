package com.dewen.config;

import lombok.Data;

@Data
public class IPInfo {
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;
}
