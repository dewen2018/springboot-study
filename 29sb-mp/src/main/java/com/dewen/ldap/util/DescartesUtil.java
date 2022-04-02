package com.dewen.ldap.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 笛卡儿积的算法 例如： 把一个 List{[1,2],[3,4],[a,b]} 转化成 List{[1,3,a],[1,3,b],[1,4,a],[1,4,b],[2,3,a],[2,3,b],[2,4,a],[2,4,b]} 数组输出
 *
 * @author Administrator
 */
public class DescartesUtil {

    /**
     * 笛卡儿积处理
     *
     * @param dimvalue 要进行笛卡儿积处理的集合
     * @return
     */
    public static <T> List<List<T>> descartes(List<List<T>> dimvalue) {
        List<List<T>> result = new ArrayList<List<T>>();
        List<T> curList = new ArrayList<T>();
        int layer = 0;
        descartes(dimvalue, result, layer, curList);
        return result;
    }

    /**
     * @param dimvalue 原List
     * @param result   通过乘积转化后的集合
     * @param layer    中间参数
     * @param curList  中间参数
     */
    public static <T> void descartes(List<List<T>> dimvalue, List<List<T>> result, int layer, List<T> curList) {
        if (layer < dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                DescartesUtil.descartes(dimvalue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    DescartesUtil.descartes(dimvalue, result, layer + 1, list);
                }
            }
        } else if (layer == dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }

}
