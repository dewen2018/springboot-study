package com.improvedthroughput.m6;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class LongTimeTask {

    public String execute(DeferredResult<String> deferredResult) {
        try {
            Thread.sleep(10 * 1000L);
            return "task finish";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
