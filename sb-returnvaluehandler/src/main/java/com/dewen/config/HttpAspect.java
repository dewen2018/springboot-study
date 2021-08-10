package com.dewen.config;

import com.alibaba.fastjson.JSON;
import com.dewen.utils.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    // 对包下所有的controller结尾的类的所有方法增强
    private final String executeExpr = "execution(* com.dewen.controller.*Controller.*(..))";

    @Around(executeExpr)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = RequestUtil.getCurrentRequest();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 2.最关键的一步:通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] objs = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                String param = parameterNames[i];
                if (param.indexOf("request") != -1 || param.indexOf("response") != -1) {
                    continue;
                }
                try {
                    sb.append(param + "=" + JSON.toJSONString(objs[i]) + ",");
                } catch (Exception e) {
                    sb.append(param + "=..." + ",");
                }
            }
        }
        logger.info("----------------------------------------------");
        logger.info("URL:{}", request.getRequestURL().toString());
        logger.info("Method:{}", request.getMethod());
        logger.info("C-M:{}.{}", methodSignature.getDeclaringTypeName(), signature.getName());
        logger.info("Ip:{}", request.getRemoteAddr());
        logger.info("Params:{}", JSON.toJSON(sb.toString()));
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Times:{}ms", endTime - startTime);
        logger.info("----------------------------------------------");
        return result;
    }

}
