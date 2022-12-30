package com.dewen.controller.groovy

import com.dewen.config.SpringContextUtil
import com.dewen.service.GroovyTestService

/**
 * @author dewen
 * @date 2022/12/30 16:25
 */
def helloWord() {
    println("hello dewen");
}

/**
 * 简易加法
 * @param a 数字a
 * @param b 数字b
 * @return 和
 */
def add(int a, int b) {
    return a + b;
}
/**
 * map转化为String
 * @param paramMap 参数map
 * @return 字符串
 */
def mapToString(Map<String, String> paramMap) {
    StringBuilder stringBuilder = new StringBuilder();
    paramMap.forEach({ key, value ->
        stringBuilder.append("key:" + key + ";value:" + value)
    })
    return stringBuilder.toString();
}

/**
 * 静态变量
 */
class Globals {
    static String PARAM1 = "静态变量"
    static int[] arrayList = [1, 2]
}

def getBean() {
    GroovyTestService groovyTestService = SpringContextUtil.getBean(GroovyTestService.class);
    groovyTestService.test()
}