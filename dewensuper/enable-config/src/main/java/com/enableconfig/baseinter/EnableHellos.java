package com.enableconfig.baseinter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloImportSelector.class) // 模块装配：接口驱动实现 优点可选bean
public @interface EnableHellos {
} 