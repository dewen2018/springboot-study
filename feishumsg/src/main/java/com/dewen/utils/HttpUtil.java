package com.dewen.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.dewen.consts.ReqConst;

import java.util.Map;

public class HttpUtil {
    public static JSONObject post(String url, Map<String, Object> paramsMap, Map<String, Object> formMap) {
        String suffix = "";
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            suffix += ("&" + entry.getKey() + "=" + entry.getValue());
        }
        return JSONObject.parseObject(HttpRequest.post(StrUtil.isEmpty(suffix) ? url : (url + "?" + suffix.substring(1)))
                .header("Authorization", ReqConst.Authorization)
                .header("Content-Type", "application/json; charset=utf-8")
                // .form(formMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body());
    }

    public static JSONObject get(String url, Map<String, Object> paramsMap) {
        String suffix = "";
        if (paramsMap != null) {
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                suffix += ("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        return JSONObject.parseObject(HttpRequest.get(StrUtil.isEmpty(suffix) ? url : (url + "?" + suffix.substring(1)))
                .header("Authorization", ReqConst.Authorization)
                .header("Content-Type", "application/json; charset=utf-8")
                // .form(formMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body());
    }
}
