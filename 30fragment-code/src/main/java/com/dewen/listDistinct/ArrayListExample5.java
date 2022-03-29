package com.dewen.listDistinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 利用 List 的 contains 方法循环遍历, 重新排序, 只添加一次数据, 避免重复
 */
public class ArrayListExample5 {
    public static void main(String[] args) {

        ArrayList<String> numbersList = new ArrayList<>(Arrays.asList("1", "1", "2", "3", "3", "3", "4", "5", "6", "6", "6", "7", "8"));
        System.out.println(numbersList);
        removeDuplicate(numbersList);
        System.out.println(numbersList);

    }

    private static void removeDuplicate(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j && list.get(i) == list.get(j)) {
                    list.remove(list.get(j));
                }
            }
        }
    }
}