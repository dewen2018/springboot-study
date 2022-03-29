package com.dewen.策略模式2;

import org.springframework.context.ApplicationContext;

/**
 * 提供各种applicationContext的方法的简单代理
 */
public class BeanUtil {
    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        BeanUtil.applicationContext = applicationContext;
    }
}