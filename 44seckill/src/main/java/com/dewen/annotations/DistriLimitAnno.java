package com.dewen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistriLimitAnno {
    String limitKey() default "limit";

    int limit() default 1;

    String seconds() default "1";
}
