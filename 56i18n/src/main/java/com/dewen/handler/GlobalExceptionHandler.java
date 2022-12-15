// package com.dewen.handler;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.StringUtils;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
//
// @Slf4j
// @RestControllerAdvice
// public class GlobalExceptionHandler {
//
//    // 引入国际化处理类
//    @Autowired
//    private MessageSourceHandler messageSourceHandler;
//
//    private String handleException(Exception e, String code) {
//        return handleException(e, code, null);
//    }
//
//    // 具体异常处理类
//    private String handleException(Exception e, String code, Object body) {
//        String msgKey = e.getMessage();
//        String msg = msgKey;
//        try {
//            msg = messageSourceHandler.getMessage(msgKey);
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//        }
//        if (StringUtils.isEmpty(msg)) {
//            if (StringUtils.isEmpty(msgKey)) {
//                msg = messageSourceHandler.getMessage(ErrorTypeEnum.INTERNAL_SERVER_ERROR.getMessage());
//            } else {
//                msg = msgKey;
//            }
//        }
//        log.error("Return Error Message : " + msg);
//        return msg;
//    }
//
//    // 请求错误异常处理
//    @ExceptionHandler(BadRequestException.class)
//    public String handleBadRequest(BadRequestException e) {
//        return handleException(e, e.getCode());
//    }
//
//    // 服务器内部异常处理
//    @ExceptionHandler(InternalServerException.class)
//    public String handleInternalServerError(InternalServerException e) {
//        return handleException(e, e.getCode());
//    }
//
//    // 调用其他服务异常处理
//    @ExceptionHandler(InvokeOtherServerException.class)
//    public String handleInvokeOtherServerError(InvokeOtherServerException e) {
//        return handleException(e, e.getCode(), e.getBody());
//    }
//
//    // DAO异常处理
//    @ExceptionHandler(DaoException.class)
//    public String handleDaoError(DaoException e) {
//        return handleException(e, e.getCode());
//    }
// }