package com.dewen.ldap.nineox.util;

import com.dewen.ldap.jdbc.builder.QueryBuilder;
import com.dewen.ldap.jdbc.builder.WhereBuilder;
import com.dewen.ldap.util.StrUtil;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class WhereBuilderUtil {

    public static WhereBuilder create(String[] paramNames, Object[] paramValues) {
        if (paramNames == null || paramNames.length == 0 || paramValues == null || paramValues.length == 0 || paramNames.length != paramValues.length) {
            return null;
        }
        WhereBuilder builder = new WhereBuilder();
        for (int i = 0; i < paramValues.length; i++) {
            Object value = paramValues[i];
            String name = paramNames[i];
            setProperty(builder, name, value, "=");
        }
        return builder;
    }

    public static WhereBuilder create(String[] paramNames, Object[] paramValues, String customCondition) {
        if(StrUtil.isBlank(customCondition)) {
            if (paramNames == null || paramNames.length == 0 || paramValues == null || paramValues.length == 0 || paramNames.length != paramValues.length) {
                return null;
            }
        } else {
            if (paramValues != null && paramValues.length > 0) {
                if(paramNames == null || paramNames.length == 0 || paramNames.length != paramValues.length) {
                    return null;
                }
            }
        }
        WhereBuilder builder = new WhereBuilder();
        if (paramValues != null && paramValues.length > 0) {
            for (int i = 0; i < paramValues.length; i++) {
                Object value = paramValues[i];
                String name = paramNames[i];
                setProperty(builder, name, value, "=");
            }
        }
        builder.setCustomCondition(customCondition);
        return builder;
    }

    public static WhereBuilder create(QueryBuilder queryBuilder) {
        if(StrUtil.isBlank(queryBuilder.getCustomCondition())) {
            if (queryBuilder == null || queryBuilder.invail()) {
                return null;
            }
        }

        WhereBuilder builder = new WhereBuilder();
        if (queryBuilder != null && !queryBuilder.invail()) {
            String[] names = queryBuilder.getName();
            String[] operators = queryBuilder.getOperator();
            Object[] values = queryBuilder.getValue();
            for (int i = 0; i < values.length; i++) {
                String name = names[i];
                if (StrUtil.isBlank(name)) {
                    continue;
                }
                Object value = values[i];
                if (value == null) {
                    continue;
                }
                String operator = operators == null || i >= operators.length ? "=" : operators[i];
                setProperty(builder, name, value, operator);
            }
        }
        builder.setCustomCondition(queryBuilder.getCustomCondition());
        return builder;
    }

    public static void setProperty(WhereBuilder builder, String name, Object value, String operator) {
        if (value instanceof Time) {
            builder.setDate(name, (Time) value);
        } else if (value instanceof Timestamp) {
            builder.setTimestamp(name, (Timestamp) value);
        } else if (value instanceof Date) {
            builder.setDate(name, (Date) value);
        } else if (value instanceof Integer) {
            builder.setInt(name, (Integer) value);
        } else if (value instanceof Long) {
            builder.setLong(name, (Long) value);
        } else if (value instanceof Float) {
            builder.setFloat(name, (Float) value);
        } else if (value instanceof Double) {
            builder.setDouble(name, (Double) value);
        } else if (value instanceof Boolean) {
            builder.setBool(name, (Boolean) value);
//        } else if (value instanceof Onum) {
//            builder.setEnum(name, (Onum<?, ?>) value);
        } else {
            if (QueryBuilder.COMPOPERATOR_LIKE.equals(operator)) {
                value = "%" + value.toString().trim() + "%";
            }
            builder.setString(name, (String) value);
        }
        builder.setOperator(operator);
    }
}
