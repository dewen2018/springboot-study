package com.dewen.ldap.nineox.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FiledName {
    String filedName() default "";
    String formatter() default "";
}
