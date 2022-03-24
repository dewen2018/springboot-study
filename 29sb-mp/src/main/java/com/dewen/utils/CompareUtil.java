package com.dewen.utils;

import java.util.ArrayList;
import java.util.List;

public class CompareUtil {
    public static boolean cmp(List l1, List l2) {
        ArrayList cp = new ArrayList<>(l1);
        for (Object o : l2) {
            if (!cp.remove(o)) {
                return false;
            }
        }
        return cp.isEmpty();
    }
}
