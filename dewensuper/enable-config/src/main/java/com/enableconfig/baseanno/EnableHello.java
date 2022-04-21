package com.enableconfig.baseanno;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloConfiguration.class) // 模块装配：注解驱动实现
public @interface EnableHello {
}  