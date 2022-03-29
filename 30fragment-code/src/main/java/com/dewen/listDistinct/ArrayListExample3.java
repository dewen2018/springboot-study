package com.dewen.listDistinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 利用 HashSet 不能添加重复数据的特性 由于 HashSet 不能保证添加顺序，所以只能作为判断条件保证顺序
 */
public class ArrayListExample3 {
    public static void main(String[] args) {

        ArrayList<String> numbersList = new ArrayList<>(Arrays.asList("1", "1", "2", "3", "3", "3", "4", "5", "6", "6", "6", "7", "8"));
        System.out.println(numbersList);
        removeDuplicate(numbersList);
        System.out.println(numbersList);

    }

    private static void removeDuplicate(List<String> list) {
        HashSet<String> set = new HashSet<String>(list.size());
        List<String> result = new ArrayList<String>(list.size());
        for (String str : list) {
            if (set.add(str)) {
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
    }
}