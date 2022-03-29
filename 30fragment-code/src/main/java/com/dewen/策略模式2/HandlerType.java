package com.dewen.策略模式2;

import java.lang.annotation.*;

/**
 * @author Dewen
 * @date 2022/3/28 10:39:02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandlerType {
    Class type();

    String value() default "";
}
