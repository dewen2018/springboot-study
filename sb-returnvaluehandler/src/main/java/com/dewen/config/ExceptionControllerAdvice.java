package com.dewen.config;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    private static final int STATUS_CODE_FAIL = -1;

    @ResponseBody
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Map<String, Object> exceptionHandler(Exception ex) {
        String msg = ex.getMessage();
        if (StrUtil.isBlank(msg)) {
            msg = "服务器处理异常";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", STATUS_CODE_FAIL);
        map.put("message", msg);
        map.put("data", ex.getClass().getSimpleName());
        // 发生异常进行日志记录，写入数据库或者其他处理，此处省略
        // if (ex instanceof IllegalArgumentException || ex instanceof NotLoginException) {
        if (ex instanceof IllegalArgumentException) {
            logger.error(ex.getMessage());
        } else {
            logger.error("#SystemException:{}", ex);
        }
        return map;
    }

    /**
     * 处理上传异常
     *
     * @param t
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MultipartException.class)
    public Map<String, Object> handleAll(Throwable t) throws Exception {
        logger.error("file.upload.error:{}", t.getMessage());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", STATUS_CODE_FAIL);
        map.put("message", "文件太大，超过最大限制5M");
        map.put("data", t.getClass().getSimpleName());

        return map;
    }
}
