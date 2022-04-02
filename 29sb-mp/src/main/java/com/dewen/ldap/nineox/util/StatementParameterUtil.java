package com.dewen.ldap.nineox.util;


import com.dewen.ldap.jdbc.StatementParameter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StatementParameterUtil {

    public static StatementParameter create(List<String> paramNames, Map<String, Object> paramValues) {
        if (paramNames == null || paramNames.size() == 0 || paramValues == null || paramValues.size() == 0) {
            return new StatementParameter();
        }

        StatementParameter parameter = new StatementParameter();
        for (int i = 0; i < paramNames.size(); i++) {
            String paramName = paramNames.get(i);
            Object value = paramValues.get(paramName);
            setProperty(parameter, value);
        }
        return parameter;
    }

    public static StatementParameter create(Object[] paramValues) {
        if (paramValues == null || paramValues.length == 0) {
            return new StatementParameter();
        }
        StatementParameter parameter = new StatementParameter();
        for (int i = 0; i < paramValues.length; i++) {
            Object value = paramValues[i];
            setProperty(parameter, value);
        }
        return parameter;
    }

    public static void setProperty(StatementParameter parameter, Object value) {
        if (value instanceof Time) {
            parameter.setDate((Time) value);
        } else if (value instanceof Timestamp) {
            parameter.setTimestamp((Timestamp) value);
        } else if (value instanceof Date) {
            parameter.setDate((Date) value);
        } else if (value instanceof Integer) {
            parameter.setInt((Integer) value);
        } else if (value instanceof Long) {
            parameter.setLong((Long) value);
        } else if (value instanceof Float) {
            parameter.setFloat((Float) value);
        } else if (value instanceof Double) {
            parameter.setDouble((Double) value);
        } else if (value instanceof Boolean) {
            parameter.setBool((Boolean) value);
//        } else if (value instanceof Onum) {
//            parameter.setEnum((Onum<?, ?>) value);
        } else {
            parameter.setString((String) value);
        }
    }
}
