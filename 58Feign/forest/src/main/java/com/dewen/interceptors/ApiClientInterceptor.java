package com.dewen.interceptors;

import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class ApiClientInterceptor implements Interceptor {

    private final Log log = LogFactory.getLog(ApiClientInterceptor.class);

    @Override
    public boolean beforeExecute(ForestRequest request) {
        String accessToken = "111111111";
        request.addHeader("accessToken", accessToken);
        log.info("accessToken = " + accessToken);
        return true;
    }

    @Override
    public void onSuccess(Object data, ForestRequest request, ForestResponse response) {
        log.info("invoke Simple onSuccess");
    }

    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
        log.info("invoke Simple onError");
    }

    @Override
    public void afterExecute(ForestRequest request, ForestResponse response) {
        Map headers = request.getHeaders();
        System.out.println(headers);
        log.info("invoke Simple afterExecute");
    }
}