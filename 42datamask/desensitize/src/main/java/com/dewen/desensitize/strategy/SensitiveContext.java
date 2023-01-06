package com.dewen.desensitize.strategy;

import com.dewen.desensitize.enums.SensitiveType;
import com.dewen.desensitize.strategy.impl.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取所有策略
 */
@Component
public class SensitiveContext {

    private static final Map<SensitiveType, SensitiveStrategy> map = new ConcurrentHashMap<>();

    static {
        map.put(SensitiveType.DEFAULT, new DefaultStrategyHandle());
        map.put(SensitiveType.CHINESE_NAME, new NameStrategyHandle());
        map.put(SensitiveType.MOBILE, new MobileStrategyHandle());
        map.put(SensitiveType.FIXED_PHONE, new FixedPhoneStrategyHandle());
        map.put(SensitiveType.BANK_CARD, new BankCardStrategyHandle());
        map.put(SensitiveType.ID_CARD, new IdCardStrategyHandle());
        map.put(SensitiveType.EMAIL, new EmailStrategyHandle());
        map.put(SensitiveType.ADDRESS, new AddressStrategyHandle());
    }


    public static SensitiveStrategy get(SensitiveType sensitiveType) {

        SensitiveStrategy sensitiveStrategy = map.get(sensitiveType);
        Assert.notNull(sensitiveStrategy, "eensitiveStrategy no found!");
        return sensitiveStrategy;
    }
}
