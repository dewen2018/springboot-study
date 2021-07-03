package com.dewen.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.dewen.consts.ReqConst;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Slf4j
public class HttpUtil {
    private static String Authorization;

    public static String getAuthorization() {
        return Authorization;
    }

    public static JSONObject post(String url, Map<String, Object> paramsMap, Map<String, Object> formMap) {
        String suffix = "";
        if (paramsMap != null) {
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                suffix += ("&" + entry.getKey() + "=" + entry.getValue());
            }
            log.info("suffix:", suffix);
        }
        if (formMap != null)
            log.info("formMap:", formMap.toString());
        return JSONObject.parseObject(HttpRequest.post(StrUtil.isEmpty(suffix) ? url : (url + "?" + suffix.substring(1)))
                .header("Authorization", Authorization)
                .header("Content-Type", "application/json; charset=utf-8")
                .form(formMap)//表单内容
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
        JSONObject res = JSONObject.parseObject(HttpRequest.get(StrUtil.isEmpty(suffix) ? url : (url + "?" + suffix.substring(1)))
                .header("Authorization", Authorization)
                .header("Content-Type", "application/json; charset=utf-8")
                // .form(formMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body());
        if (99991663 == res.getInteger("code") || 99991661 == res.getInteger("code")) {
            getTenantAccessTtoken();
            return get(url, paramsMap);
        }
        return res;
    }


    /**
     * 处理post请求
     *
     * @param urlStr     请求url
     * @param data       请求体数据
     * @param properties 请求头参数
     * @return
     */
    public static JSONObject post2(String urlStr, Map<String, String> properties, JSONObject data) {
        try {
            log.info("请求url={}", urlStr);
            log.info("请求体数据data={}", data.toJSONString());
            log.info("请求头参数properties={}", properties);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Authorization", Authorization);

            if (properties != null)
                for (String key : properties.keySet()) {
                    connection.setRequestProperty(key, properties.get(key));
                }

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(data.toJSONString());
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
            JSONObject res = JSONObject.parseObject(result.toString());
            if (99991663 == res.getInteger("code") || 99991661 == res.getInteger("code")) {
                getTenantAccessTtoken();
                return post2(urlStr, properties, data);
            }
            return JSONObject.parseObject(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("post请求失败");
            return new JSONObject();
        }
    }

    /**
     * 处理put请求
     *
     * @param urlStr     请求url
     * @param data       请求体数据
     * @param properties 请求头参数
     * @return
     */
    public static JSONObject put(String urlStr, Map<String, String> properties, JSONObject data) {
        try {
            log.info("请求url={}", urlStr);
            log.info("请求体数据data={}", data.toJSONString());
            log.info("请求头参数properties={}", properties);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("PUT"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Authorization", Authorization);

            if (properties != null)
                for (String key : properties.keySet()) {
                    connection.setRequestProperty(key, properties.get(key));
                }

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(data.toJSONString());
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
            JSONObject res = JSONObject.parseObject(result.toString());
            if (99991663 == res.getInteger("code") || 99991661 == res.getInteger("code")) {
                getTenantAccessTtoken();
                return post2(urlStr, properties, data);
            }
            return JSONObject.parseObject(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("post请求失败");
            return new JSONObject();
        }
    }

    /**
     * 处理delete请求
     * @param urlStr
     * @param properties
     * @param data
     * @return
     */
    public static JSONObject delete(String urlStr, Map<String, String> properties, JSONObject data) {
        try {
            log.info("请求url={}", urlStr);
            log.info("请求头参数properties={}", properties);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("DELETE"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Authorization", Authorization);

            if (properties != null)
                for (String key : properties.keySet()) {
                    connection.setRequestProperty(key, properties.get(key));
                }

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            if (data != null) {
                log.info("请求体数据data={}", data.toJSONString());
                pw.write(data.toJSONString());
            } else {
                pw.write(new JSONObject().toJSONString());
            }
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
            JSONObject res = JSONObject.parseObject(result.toString());
            if (99991663 == res.getInteger("code") || 99991661 == res.getInteger("code")) {
                getTenantAccessTtoken();
                return post2(urlStr, properties, data);
            }
            return JSONObject.parseObject(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("post请求失败");
            return new JSONObject();
        }
    }


    /**
     * 更新token
     */

    private static void getTenantAccessTtoken() {
        JSONObject data = new JSONObject();
        data.put("app_id", ReqConst.APP_ID);
        data.put("app_secret", ReqConst.APP_SECRET);
        JSONObject res = post2(ReqConst.TENANT_ACCESS_TOKEN, null, data);
        if (0 == res.getInteger("code") && "ok".equals(res.get("msg"))) {
            Authorization = ReqConst.prefixAuthorization + res.getString("tenant_access_token");
        } else {
            log.error(res.toJSONString());
        }
    }
}
