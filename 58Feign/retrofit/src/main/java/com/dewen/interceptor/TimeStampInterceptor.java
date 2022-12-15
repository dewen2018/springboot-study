package com.dewen.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 注解式拦截器
 * 统一的拦截处理逻辑
 * 很多时候，我们希望某个接口下的某些http请求执行统一的拦截处理逻辑。
 * 这个时候可以使用注解式拦截器。使用的步骤主要分为2步：
 * 继承BasePathMatchInterceptor编写拦截处理器；
 * 接口上使用@Intercept进行标注。
 * 下面以给指定请求的url后面拼接timestamp时间戳为例，介绍下如何使用注解式拦截器。
 */
@Component
public class TimeStampInterceptor extends BasePathMatchInterceptor {

    @Override
    public Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        long timestamp = System.currentTimeMillis();
        HttpUrl newUrl = url.newBuilder()
                .addQueryParameter("timestamp", String.valueOf(timestamp))
                .build();
        Request newRequest = request.newBuilder()
                .url(newUrl)
                .build();
        return chain.proceed(newRequest);
    }
}