package com.dewen.策略模式2;

import java.util.Map;

/**
 * 处理器上下文
 *
 * @author Dewen
 * @date 2022/3/28 10:26:55
 */
public class HandlerContext {
    private Map<Class, Map<String, Class>> handlerMap;

    public HandlerContext(Map<Class, Map<String, Class>> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
     * 实例化
     *
     * @param type
     * @param value
     * @return
     */
    public <T> T getInstance(Class<T> type, String value) {
        Class clazz = handlerMap.get(type).get(value);
        if (clazz == null)
            throw new IllegalArgumentException("not found handler for type:" + type);
        return (T) BeanUtil.getBean(clazz);
    }
}
