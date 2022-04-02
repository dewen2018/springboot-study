package com.dewen.ldap.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryCompareUtil {

    public static <T> T compare(Object old, Object news) {

        Field[] newsFields = news.getClass().getDeclaredFields();
        Map<String, Object> newsMap = new HashMap<>();
        for (Field field : newsFields) {
            newsMap.put(field.getName(), getFieldsValue(field, news));
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static Object getFieldsValue(Field field, Object o) {

        Object value = null;
        if ("java.util.List".equals(field.getType().getName())) {

            List<?> list = (List<?>) getValue(field, o);
            for (Object object : list) {

            }
        } else {
            value = getValue(field, o);
        }
        return value;
    }

    public static Object getValue(Field field, Object o) {
        String getter = "get" + StrUtil.firstCharToUpperCase(field.getName());
        Method method = null;
        try {
            method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
