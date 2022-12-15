package com.dewen.client;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface MyClient {
    @Request(url = "http://baidu.com")
    String simpleRequest();

//    @Request(
//            url = "http://ditu.amap.com/service/regeo?longitude=${0}&latitude=${1}",
//            dataType = "json"
//    )
//    Map getLocation(String longitude, String latitude);

    @Request(
            url = "http://ditu.amap.com/service/regeo",
            dataType = "json"
    )
    Map getLocation(@DataParam("longitude") String longitude, @DataParam("latitude") String latitude);
}