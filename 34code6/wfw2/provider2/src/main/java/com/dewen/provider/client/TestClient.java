package com.dewen.provider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "privider")//熔断机制提示类：自定义使用fallback = OrderRemoteHystrix.class
public interface TestClient {
    @GetMapping("/hi")
    String getReturn(@RequestParam("name") String name);
}
