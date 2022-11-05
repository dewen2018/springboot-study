package com.dewen.annotations.aspects;


import com.dewen.annotations.DistriLimitAnno;
import com.dewen.config.limit.DistributedLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LimitAspect {

    @Autowired
    private DistributedLimit distributedLimit;

    @Pointcut("@annotation(com.dewen.annotations.DistriLimitAnno)")
    public void limit() {
    }

    @Before("limit()")
    public void beforeLimit(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistriLimitAnno distriLimitAnno = method.getAnnotation(DistriLimitAnno.class);
        String key = distriLimitAnno.limitKey();
        int limit = distriLimitAnno.limit();
        String seconds = distriLimitAnno.seconds();
        Boolean exceededLimit = distributedLimit.distributedRateLimit(key, String.valueOf(limit), seconds);
        if (!exceededLimit) {
            throw new RuntimeException("exceeded limit");
        }
    }

}
