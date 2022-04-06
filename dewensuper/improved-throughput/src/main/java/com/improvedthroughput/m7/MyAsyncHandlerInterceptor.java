//package com.improvedthroughput.m7;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.AsyncHandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class MyAsyncHandlerInterceptor implements AsyncHandlerInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger(MyAsyncHandlerInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////  HandlerMethod handlerMethod = (HandlerMethod) handler;
//        logger.info(Thread.currentThread().getName() + "服务调用完成，返回结果给客户端");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (null != ex) {
//            System.out.println("发生异常:" + ex.getMessage());
//        }
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        // 拦截之后，重新写回数据，将原来的hello world换成如下字符串
//        String resp = "my name is chhliu!";
//        response.setContentLength(resp.length());
//        response.getOutputStream().write(resp.getBytes());
//        logger.info(Thread.currentThread().getName() + " 进入afterConcurrentHandlingStarted方法");
//    }
//}