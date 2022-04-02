package com.dewen.ldap.nineox.util;

import com.dewen.ldap.jdbc.builder.QueryBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class QueryBuilderUtil {

    public static QueryBuilder create(String tableName, String[] paramNames, Object[] paramValues) {
        if (paramNames == null || paramNames.length == 0 || paramValues == null || paramValues.length == 0 || paramNames.length != paramValues.length) {
            return null;
        }
        QueryBuilder builder = new QueryBuilder(tableName);
        for (int i = 0; i < paramValues.length; i++) {
            Object value = paramValues[i];
            String name = paramNames[i];
            setProperty(builder, name, value);
        }
        return builder;
    }

    public static void setProperty(QueryBuilder builder, String name, Object value) {
        if (value instanceof Time) {
            builder.addWhere(name, (Time) value);
        } else if (value instanceof Timestamp) {
            builder.addWhere(name, (Timestamp) value);
        } else if (value instanceof Date) {
            builder.addWhere(name, (Date) value);
        } else if (value instanceof Integer) {
            builder.addInt(name, (Integer) value);
        } else if (value instanceof Long) {
            builder.addLong(name, (Long) value);
        } else if (value instanceof Float) {
            builder.addWhere(name, (Float) value);
        } else if (value instanceof Double) {
            builder.addWhere(name, (Double) value);
        } else if (value instanceof Boolean) {
            builder.addBool(name, (Boolean) value);
//        } else if (value instanceof Inum) {
//            builder.addEnum(name, (Inum) value);
//        } else if (value instanceof Snum) {
//            builder.addEnum(name, (Snum) value);
        } else {
            builder.addString(name, (String) value);
        }
    }
}
