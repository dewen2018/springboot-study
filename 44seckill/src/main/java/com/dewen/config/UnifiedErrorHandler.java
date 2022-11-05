package com.dewen.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一的controller错误处理
 */
@Slf4j
@ControllerAdvice
public class UnifiedErrorHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Long processException(HttpServletRequest req, Exception e) {
        log.info("error:{}", e.getMessage());
        return 0L;
    }

}
