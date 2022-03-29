package com.dewen.策略模式2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HandlerProcessor implements BeanFactoryPostProcessor {
    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<Class, Map<String, Class>> context = new HashMap<>(16);
        Arrays.stream(beanFactory.getBeanNamesForAnnotation(HandlerType.class)).collect(Collectors.groupingBy(className ->
                Arrays.stream(beanFactory.getType(className).getAnnotations())
                        .filter(annotation -> annotation instanceof HandlerType)
                        .map(annotation -> ((HandlerType) annotation).type()).findFirst().get()
        )).forEach((clazz, list) -> context.put(clazz, list.stream().collect(Collectors.toMap(className ->
                        Arrays.stream(beanFactory.getType(className).getAnnotations())
                                .filter(annotation -> annotation instanceof HandlerType)
                                .map(annotation -> ((HandlerType) annotation).value()).findFirst().get(),
                className -> beanFactory.getType(className), (oldValue, newValue) -> newValue))));

        HandlerContext handlerContext = new HandlerContext(context);

        beanFactory.registerSingleton(HandlerContext.class.getName(), handlerContext);
    }
}