package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dewen.client.MyClient;
import com.dewen.client.MyClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: dewen
 * @Date: 2020/8/13 21:48
 * @Description:
 */
@RestController
@RequestMapping("/sys")
public class AdminController {
    @Autowired
    private MyClient myClient;
    @Autowired
    private MyClient2 myClient2;


    @RequestMapping("/test")
    public void test() {
        // 调用接口
//        Map result = myClient.getLocation("121.475078", "31.223577");
//        System.out.println(JSON.toJSONString(result,true));
//        String result = myClient.simpleRequest();
        JSONObject request = new JSONObject() {{
            put("zjhm", "C354458(1)");
        }};
        JSONObject result = myClient2.findXghcsjBmdListByZjhm(request);
        System.out.println(JSON.toJSONString(result, true));
    }


}
