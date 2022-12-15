package com.dewen.client;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.Request;

public interface MyClient2 {

    @Request(
            url = "http://2020.pengqi.cn:8100/yqck2020/xghcsjXxcjWeb/findXghcsjBmdListByZjhm",
            type = "POST",
            contentType = "application/json",
            dataType = "json",
            data = "${json($0)}"
    )
    JSONObject findXghcsjBmdListByZjhm(JSONObject request);
}