package com.dewen.desensitize.strategy.impl;


import com.dewen.desensitize.enums.SensitiveType;
import com.dewen.desensitize.strategy.SensitiveStrategy;

import java.util.regex.Pattern;

/**
 * 身份证号脱敏 330127199911114444转为330127199911114444****
 */
public class IdCardStrategyHandle implements SensitiveStrategy {

    /**
     * 身份证号码位数限制
     */
    public final static String ID_CARD = "^\\d{15}|(\\d{17}[0-9,x,X])$";

    @Override
    public SensitiveType getType() {
        return SensitiveType.ID_CARD;
    }

    @Override
    public String handle(Object object, String fillValue) {
        if (object == null) {
            return null;
        }
        //字段原始值
        String value = object.toString();
        //如果身份证号不符合格式 直接返回 不进行脱敏
        if (!Pattern.matches(ID_CARD, value)) {
            return value;
        }
        //身份证号脱敏
        return this.rightFill(value, fillValue);
    }
}
