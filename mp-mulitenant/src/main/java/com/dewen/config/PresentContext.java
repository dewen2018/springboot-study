package com.dewen.config;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 当前系统的上下文
 * dj 2021年8月10日11:42:00
 */
@Component
public class PresentContext {

    private static final String KEY_CURRENT_TENANT_ID = "KEY_CURRENT_PROVIDER_ID";
    private static final Map<String, Object> M_CONTEXT = new ConcurrentHashMap<>();

    public void setCurrentTenantId(Long tenantId) {
        M_CONTEXT.put(KEY_CURRENT_TENANT_ID, tenantId);
    }

    public Long getCurrentTenantId() {
        return (Long) M_CONTEXT.get(KEY_CURRENT_TENANT_ID);
    }
}