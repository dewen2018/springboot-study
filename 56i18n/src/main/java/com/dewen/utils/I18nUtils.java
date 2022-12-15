package com.dewen.utils;

import com.dewen.handler.MessageSourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class I18nUtils {
    private static MessageSourceHandler handler;

    @Autowired
    public I18nUtils(MessageSourceHandler handler) {
        I18nUtils.handler = handler;
    }

    /**
     * 根据Kye取国际化的值
     */
    public static String getMessage(String messageKey) {
        String message = handler.getMessage(messageKey);
        return message;
    }
}
