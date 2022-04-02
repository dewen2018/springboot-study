package com.dewen.ldap;

import com.dewen.ldap.util.StrUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MyBatchPreparedStatementSetter<T> implements BatchPreparedStatementSetter {

    final List<T> temList;

    /**
     * 通过构造函数把要插入的数据传递进来处理
     */
    public MyBatchPreparedStatementSetter(List<T> list) {
        temList = list;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {

        T t = (T) temList.get(i);

        Field[] fields = t.getClass().getDeclaredFields();
        int n = 1;
        for (Field field : fields) {
            Class<?> type = field.getType();
            String getter = pareGetName(field.getName(), type);
            try {
                Method method = t.getClass().getMethod(getter, new Class[]{});
                Object value = method.invoke(t, new Object[]{});
                ps.setObject(n, value);
                n++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String pareGetName(String fieldName, Class<?> type) {

        if (StrUtil.isEmpty(fieldName)) {
            return null;
        }
        String prefix = "get";
        if ("boolean".equals(type.getName())) {
            prefix = "is";
        }
        String getter = prefix + StrUtil.firstCharToUpperCase(fieldName);
        return getter;
    }

    @Override
    public int getBatchSize() {
        return temList.size();
    }

}
