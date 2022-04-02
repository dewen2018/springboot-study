package com.dewen.ldap.nineox.util;

import com.dewen.ldap.jdbc.builder.InsertBuilder;
import com.dewen.ldap.nineox.annotation.Property;
import com.dewen.ldap.nineox.session.SessionUtil;
import com.dewen.ldap.util.DateUtil;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.core.annotation.AnnotationUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class InsertBuilderUtil {
    public static final String CREATEUSERID = "createUserId";
    public static final String CREATETIME = "createTime";
    public static final String LASTUPDATEUSERID = "lastUpdateUserId";
    public static final String LASTUPDATETIME = "lastUpdateTime";
    public static final String ORGID = "orgId";
    public static final String CREATESSOORGID = "ssoOrgId";
    public static final String CREATESSODEPID = "ssoDepId";

    public static <T> InsertBuilder create(T object, String tableName) {
        if (object == null || tableName == null || tableName.length() == 0) {
            return null;
        }
        PropertyUtilsBean propertyUtilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
        PropertyDescriptor[] origDescriptors = propertyUtilsBean.getPropertyDescriptors(object);
        if (origDescriptors == null || origDescriptors.length == 0) {
            return null;
        }
        InsertBuilder builder = new InsertBuilder(tableName);
        try {
            for (PropertyDescriptor propertyDescriptor : origDescriptors) {
                String name = propertyDescriptor.getName();
                if (propertyUtilsBean.isReadable(object, name) && propertyUtilsBean.isWriteable(object, name)) {
                    // 判断是否是数据库字段
                    Property readMethodAnnotation = AnnotationUtils.findAnnotation(propertyUtilsBean.getReadMethod(propertyDescriptor), Property.class);
                    Property writeMethodAnnotation = AnnotationUtils.findAnnotation(propertyUtilsBean.getWriteMethod(propertyDescriptor), Property.class);
                    if ((readMethodAnnotation == null || readMethodAnnotation.dbColumn()) && (writeMethodAnnotation == null || writeMethodAnnotation.dbColumn())) {
                        // 是数据库字段
                        Object value = propertyUtilsBean.getSimpleProperty(object, name);
                        if (CREATEUSERID.equals(name) || LASTUPDATEUSERID.equals(name)) {
                            // 默认为当前登录用户id
                            if (value == null || (Long) value == 0) {
                                value = SessionUtil.getUid();
                            }
                        } else if (CREATETIME.equals(name)) {
                            // 默认为当前时间
                            value = DateUtil.getCurrentDateTime();
                        } else if (LASTUPDATETIME.equals(name)) {
                            // 默认为当前时间
                            value = DateUtil.getCurrentDateTime();
                        } else if (ORGID.equals(name)) {
                            if (value == null || (Long) value == 0) {
                                value = SessionUtil.getOrgId();
                            }
                        } else if (CREATESSOORGID.equals(name)) {
                            // 当前登录用户所属底层账户机构
                            if (value == null || "".equals(((String)value).trim())) {
                                value = SessionUtil.getSsoOrgId();
                            }
                        } else if (CREATESSODEPID.equals(name)) {
                            // 当前登录用户所属底层账户部门
                            if (value == null || "".equals(((String)value).trim())) {
                                value = SessionUtil.getSsoDepId();
                            }
                        } else {
                            if (value == null && (writeMethodAnnotation == null || writeMethodAnnotation.autoSetVal())) {
                                // 如果为null，初始化一个默认值
                                value = getDefaultValue(propertyDescriptor.getPropertyType());
                            }
                        }
                        if (value != null) {
                            setProperty(builder, name, value);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return builder;
    }

    public static void setProperty(InsertBuilder builder, String name, Object value) {
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
        } else if (value instanceof String) {
            builder.setString(name, value == null ? "" : value.toString().trim());
        }
//        else {
//            builder.setString(name, value == null ? "" : Json.toJson(value));
//        }
    }

    public static Object getDefaultValue(Class<?> clazz) {
        if (Time.class.isAssignableFrom(clazz)) {
            return null;
        } else if (Timestamp.class.isAssignableFrom(clazz)) {
            return null;
        } else if (Date.class.isAssignableFrom(clazz)) {
            return null;
        } else if (Integer.class.isAssignableFrom(clazz)) {
            return new Integer(0);
        } else if (Long.class.isAssignableFrom(clazz)) {
            return new Long(0);
        } else if (Float.class.isAssignableFrom(clazz)) {
            return new Float(0);
        } else if (Double.class.isAssignableFrom(clazz)) {
            return new Double(0);
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return Boolean.FALSE;
        } else {
            return "";
        }
    }
}
