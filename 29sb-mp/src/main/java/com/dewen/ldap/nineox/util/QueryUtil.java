package com.dewen.ldap.nineox.util;

import com.dewen.ldap.Query;
import com.dewen.ldap.jdbc.builder.QueryBuilder;
import com.dewen.ldap.util.DateUtil;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class QueryUtil {

    public static Map<String, String> setMap(Query modelQuery) {
        Field[] fields = modelQuery.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>();
        for (Field field : fields) {
            String value = field.getName();
            String className = field.getType().getSimpleName();
            map.put(className, value);
        }
        return map;
    }

    /**
     * 组装公用查询部分
     *
     * @param query
     * @param modelQuery
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void assemble(QueryBuilder query, Query modelQuery, Enum<?>... enums) {
        for (Enum<?> en : enums) {
            if (en != null) {
                try {
                    Map<String, String> setMap = setMap(modelQuery);
                    Class<?> c = en.getClass();
                    String name = c.getSimpleName();
                    String key = setMap.get(name);
                    Object value = PropertyUtils.getSimpleProperty(en, "key");
                    query.add(key, QueryBuilder.COMPOPERATOR_DENGYU, value.toString());
                } catch (Exception e) {

                }
            }
        }
        // 日期
        Query.DateColumnFilter dateColumnFilter = modelQuery.getDateFilter();
        if (dateColumnFilter != null) {
            if (dateColumnFilter.getStartDate() != null) {
                query.add(dateColumnFilter.getName(), QueryBuilder.COMPOPERATOR_DAYU_DENGYU, DateUtil.getStartTimeOfDate(dateColumnFilter.getStartDate()));
            }
            if (dateColumnFilter.getEndDate() != null) {
                query.add(dateColumnFilter.getName(), QueryBuilder.COMPOPERATOR_XIAOYU_DENGYU, DateUtil.getEndTimeOfDate(dateColumnFilter.getEndDate()));
            }
        }
        // 关键字
        Query.ColumnFilter filter = modelQuery.getFilter();
        if (filter != null) {
            query.add(modelQuery.getFilter().getName(), QueryBuilder.COMPOPERATOR_LIKE, modelQuery.getFilter().getValue());
        }
    }

    public static String getSqlWhere(Query modelQuery) {
        String sql = "";
        Query.DateColumnFilter dateColumnFilter = modelQuery.getDateFilter();
        if (dateColumnFilter != null) {
            if (dateColumnFilter.getStartDate() != null) {
                sql += " and " + dateColumnFilter.getName() + " >= '" + DateUtil.format2(DateUtil.getStartTimeOfDate(dateColumnFilter.getStartDate())) + "'";
            }
            if (dateColumnFilter.getEndDate() != null) {
                sql += " and " + dateColumnFilter.getName() + " <= '" + DateUtil.format2(DateUtil.getStartTimeOfDate(dateColumnFilter.getEndDate())) + "'";
            }
        }
        // 关键字
        Query.ColumnFilter filter = modelQuery.getFilter();
        if (filter != null) {
            sql += " and " + filter.getName() + " like '%" + filter.getValue() + "%'";
        }
        return sql;

    }
}
