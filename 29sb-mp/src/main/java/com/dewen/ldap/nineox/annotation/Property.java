package com.dewen.ldap.nineox.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Property {

    // 属性描述
    String text() default "";

    // 是否是数据库列
    boolean dbColumn() default true;

    // 必填
    boolean required() default false;

    // 自动给默认值
    boolean autoSetVal() default true;

}
