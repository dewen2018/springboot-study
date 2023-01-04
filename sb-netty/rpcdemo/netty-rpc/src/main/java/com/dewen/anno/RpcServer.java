package com.dewen.anno;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RpcServer {
}