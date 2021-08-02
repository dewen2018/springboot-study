package com.dewen.annotations;

import java.lang.annotation.*;

/**
 * 原本数据结构
 * Created by Dj on 2021.08.02
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseOriginalData {
}
