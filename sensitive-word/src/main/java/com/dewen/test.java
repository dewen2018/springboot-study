package com.dewen;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;
import org.apache.commons.codec.Charsets;

public class test {

    public static Map sensitiveMap = null;

    public static void main(String[] args) {

        Set<String> sensitiveSet = txt2String();

        System.out.println("---------1-----------" + System.currentTimeMillis());
        addSensitiveMap(sensitiveSet);
        System.out.println("---------2-----------" + System.currentTimeMillis());
        // System.out.println(JSONObject.toJSONString(sensitiveMap));
        String testString = "娘西皮操他妈sb他妈娘西皮操他妈sb他妈娘西皮操他妈sb他妈娘西皮操他妈sb他妈娘西皮操他妈sb他妈";
        Long start = System.currentTimeMillis();
        List<String> senList = checkSensitive(testString);
        Long end = System.currentTimeMillis();
        System.out.println("---------敏感词校验处理时间-----------" + (end - start));
        System.out.println(JSONObject.toJSONString(senList));
    }

    private static List<String> checkSensitive(String testString) {

        //去除无意义字符
        testString = testString.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)+]", "");

        char[] test = testString.toCharArray();
        int start = 0;//用于截取字符串的开始位置
        int senStart = 0;//敏感字符开始
        Map nowMap = sensitiveMap;
        boolean flag = false;
        List<String> senList = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            nowMap = (Map) nowMap.get(test[i]);//获取
            if (nowMap == null) {//如果没获取到该字符下的数组，直接返回，开始下一轮循环
                if (flag) {
                    i = senStart;
                    flag = false;
                }
                senStart = start = i + 1;
                nowMap = sensitiveMap;
                continue;
            } else {//获取到Map,说明该字符为敏感词库中的数据
                flag = true;
                if ("1".equals(nowMap.get("isEnd"))) {//如果该词结束，将该敏感词填入列表中，并初始化开始数
                    senList.add(testString.substring(start, i + 1) + start);
                    start = i + 1;
                    flag = false;
                    nowMap = sensitiveMap;
                }
            }
        }
        return senList;
    }

    private static void addSensitiveMap(Set<String> sensitiveSet) {
        sensitiveMap = new HashMap(sensitiveSet.size());
        String key = null;
        Map nowMap = null;
        Map nowWorMap = null;
        Iterator<String> ite = sensitiveSet.iterator();
        while (ite.hasNext()) {
            key = ite.next();
            nowMap = sensitiveMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);

                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    nowWorMap = new HashMap();
                    nowWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, nowWorMap);
                    nowMap = nowWorMap;
                }
                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 读取txt文件的内容
     *
     * @return 返回文件内容
     */
    public static Set<String> txt2String() {
        String path = "/static/censorWords.txt";
        InputStream inputStream = ProjectnameApplication.class.getResourceAsStream(path);
        Set<String> sensitiveSet = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));//构造一个BufferedReader类来读取文件
            String s = "";
            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                sensitiveSet.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveSet;
    }

}