package com.dewen.策略模式2;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration implements ApplicationContextAware {
    /**
     * 不知道为什么加了ConditionalOnProperty 不生效
     * 查明原因是需要在application中配置
     * dewen
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "base.component", name = "handler", havingValue = "true")
    public HandlerProcessor handlerProcessor() {
        // 去扫描
        return new HandlerProcessor();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.setApplicationContext(applicationContext);
    }
}