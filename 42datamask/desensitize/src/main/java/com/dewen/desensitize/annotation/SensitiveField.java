package com.dewen.desensitize.annotation;

import com.dewen.desensitize.enums.SensitiveType;

import java.lang.annotation.*;

/**
 * 对需要脱敏的字段加上该注解
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface SensitiveField {

    /**
     * 脱敏类型
     */
    SensitiveType value();

    /**
     * 填充值
     */
    String fillValue() default "*";

}
