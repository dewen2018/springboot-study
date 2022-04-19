package com.dewen.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 实际工作中大多数一个服务既是其它服务的消费者又有可能是服务的提供者，所以我们也就不用刻意的取区分开
 * cloud-service-hystrix: 作为服务方的工程
 * cloud-consumer-hystrix：通过hystrix调用cloud-service-hystrix的接口
 */
@EnableFeignClients
@EnableCircuitBreaker//服务容错_保护断路器
@EnableEurekaClient//表明自己是一个eurekaclient
@SpringBootApplication
public class Provider2Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider2Application.class, args);
    }

}
