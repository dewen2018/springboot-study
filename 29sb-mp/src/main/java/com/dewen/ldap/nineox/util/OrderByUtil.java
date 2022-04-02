package com.dewen.ldap.nineox.util;

public class OrderByUtil {

    public static String getSql(String[] sortColumn, boolean[] asc) {
        if (sortColumn == null || asc == null || sortColumn.length == 0) {
            return "";
        }

        StringBuffer result = new StringBuffer(256);
        result.append(" order by ");
        for (int i = 0; i < sortColumn.length; i++) {
            String order = asc == null || i >= asc.length || asc[i] ? " asc" : " desc";
            result.append(sortColumn[i]).append(order);
            if (i != asc.length - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
