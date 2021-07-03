//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dewen.config;

import com.baomidou.mybatisplus.core.enums.SqlLike;

public class SqlUtils {
    private static final SqlFormatter SQL_FORMATTER = new SqlFormatter();

    public SqlUtils() {
    }

    /** @deprecated */
    @Deprecated
    public static String sqlFormat(String boundSql, boolean format) {
        if (format) {
            try {
                return SQL_FORMATTER.format(boundSql);
            } catch (Exception var3) {
            }
        }

        return boundSql;
    }

    public static String concatLike(Object str, SqlLike type) {
        switch(type) {
        case LEFT:
            return "%" + str;
        case RIGHT:
            return str + "%";
        default:
            return "%" + str + "%";
        }
    }
}
