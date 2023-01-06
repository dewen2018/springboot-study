package com.dewen.desensitize.strategy.impl;


import com.dewen.desensitize.enums.SensitiveType;
import com.dewen.desensitize.strategy.SensitiveStrategy;

import java.util.regex.Pattern;

/**
 * 座机电话号脱敏 0211-8711882转为0211-871****
 */
public class FixedPhoneStrategyHandle implements SensitiveStrategy {

    /**
     * 身份证号码位数限制 匹配形式如 0511-4405222 或 021-87888822
     */
    public final static String FIXED_PHONE = "^\\d{3}-\\d{7,8}|\\d{4}-\\d{7,8}$";

    @Override
    public SensitiveType getType() {
        return SensitiveType.FIXED_PHONE;
    }

    @Override
    public String handle(Object object, String fillValue) {
        if (object == null) {
            return null;
        }
        //字段原始值
        String value = object.toString();
        //如果座机不符合格式 直接返回 不进行脱敏
        if (!Pattern.matches(FIXED_PHONE, value)) {
            return value;
        }
        //座机脱敏
        return this.rightFill(value, fillValue);
    }
}
