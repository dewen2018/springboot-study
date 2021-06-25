package com.dewen.listDistinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * LinkedHashSet 是在一个 ArrayList 删除重复数据的最佳方法。LinkedHashSet 在内部完成两件事：
 * 1.删除重复数据
 * 2.保持添加到其中的数据的顺序
 */
public class ArrayListExample1 {
    public static void main(String[] args) {

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        System.out.println(numbersList);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);

        System.out.println(listWithoutDuplicates);
    }
}