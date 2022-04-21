package com.improvedthroughput.m6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 假设我们现在要实现这样一个功能：浏览器要实时展示服务端计算出来的数据。
 * 一种可能的实现是：浏览器频繁（例如定时1秒）向服务端发起请求以获得服务端数据。但定时请求并不能“实时”反应服务端的数据变化情况。
 * 若定时周期为S，则数据延迟周期最大即为S。若想缩短数据延迟周期，则应使S尽量小，而S越小，浏览器向服务端发起请求的频率越高，又造成网络握手次数越多，影响了效率。因此，此场景应使用服务端实时推送技术。
 * <p>
 * 这里说是推送，其实还是基于请求-响应机制，只不过发起的请求会在服务端挂起，直到请求超时或服务端有数据推送时才会做出响应，响应的时机完全由服务端控制。所以，整体效果看起来就像是服务端真的在“实时推送”一样。
 * <p>
 * 可以利用SpringMVC的DeferredResult来实现异步长连接的服务端实时推送。
 */
@RestController
public class AsyncDeferredController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LongTimeTask taskService;
    /**
     * 线程池
     */
    public static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(10);

    @Autowired
    public AsyncDeferredController(LongTimeTask taskService) {
        this.taskService = taskService;
    }

    /**
     * 这里taskService要么是个线程，要么这里使用threadPool调用，否则不会异步执行。
     */
    @GetMapping("/deferred")
    public DeferredResult<String> executeSlowTask() {
        // 设置5s 超时
        DeferredResult<String> deferredResult = new DeferredResult<>(5L * 1000);
        // 超时的回调方法
        deferredResult.onTimeout(new Runnable() {

            @Override
            public void run() {
                logger.info(Thread.currentThread().getName() + " onTimeout");
                // 返回超时信息
                deferredResult.setErrorResult("time out!");
            }
        });

        // 处理完成的回调方法，无论是超时还是处理成功，都会进入这个回调方法
        deferredResult.onCompletion(new Runnable() {

            @Override
            public void run() {
                logger.info(Thread.currentThread().getName() + " onCompletion");
            }
        });

        // 调用长时间执行任务
        logger.info(Thread.currentThread().getName() + "进入executeSlowTask方法");
//        String res = taskService.execute(deferredResult);
//        deferredResult.setResult(res);
        FIXED_THREAD_POOL.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步执行线程:" + Thread.currentThread().getName());
                try {
                    String str = taskService.execute(deferredResult);
                    Thread.sleep(1000);
                    deferredResult.setResult("这是【异步】的请求返回: " + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // 当长时间任务中使用deferred.setResult("world");这个方法时，会从长时间任务中返回，继续controller里面的流程
        logger.info(Thread.currentThread().getName() + "从executeSlowTask方法返回");
        return deferredResult;
    }
}