package com.dewen.config;

import com.dewen.annotations.ResponseOriginalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dj on 2021.08.02
 */
@Slf4j
public class EdgeHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    private RequestResponseBodyMethodProcessor target;

    public EdgeHandlerMethodReturnValueHandler(RequestResponseBodyMethodProcessor target) {
        this.target = target;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        log.warn("HandlerMethodReturnValueHandlerProxy:supportsReturnType");
        //我添加了一个自定义注解ResponseData，不知道怎么写可以直接复制ResponseBody源码
        // return methodParameter.hasMethodAnnotation(ResponseOriginalData.class) || methodParameter.hasMethodAnnotation(ResponseBody.class);
        return target.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        log.warn("HandlerMethodReturnValueHandlerProxy:handleReturnValue");
        if (methodParameter.hasMethodAnnotation(ResponseOriginalData.class)) {
            target.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            //如果Controller中使用了我的自定义注解，那么对返回值进行封装
            Map<String, Object> res = new HashMap<>();
            res.put("code", "1");
            res.put("message", "success");
            res.put("data", o);
            target.handleReturnValue(res, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return target.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
}