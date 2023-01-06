package com.dewen.desensitize.strategy.impl;

import com.dewen.desensitize.enums.SensitiveType;
import com.dewen.desensitize.strategy.SensitiveStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * 默认脱敏方式
 */
public class DefaultStrategyHandle implements SensitiveStrategy {


    @Override
    public SensitiveType getType() {
        return SensitiveType.DEFAULT;
    }

    @Override
    public String handle(Object object, String fillValue) {
        if (object == null) {
            return null;
        }
        //字段原始值
        String value = object.toString();
        SensitiveType type = getType();
        int end = type.getEnd();
        int length = StringUtils.length(value);
        if (end < length) {
            return this.rightFill(value, fillValue);
        }
        return value;
    }
}
