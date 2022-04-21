package com.dewen.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 实际工作中大多数一个服务既是其它服务的消费者又有可能是服务的提供者，所以我们也就不用刻意的取区分开
 * cloud-service-hystrix: 作为服务方的工程
 * cloud-consumer-hystrix：通过hystrix调用cloud-service-hystrix的接口
 */

/**
 * spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），
 * @EnableDiscoveryClient基于spring-cloud-commons, @EnableEurekaClient基于spring-cloud-netflix。
 * 其实用更简单的话来说，就是如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，
 * 如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
 * 注解@EnableEurekaClient上有@EnableDiscoveryClient注解，可以说基本就是EnableEurekaClient有@EnableDiscoveryClient的功能，
 * 另外上面的注释中提到，其实@EnableEurekaClientz注解就是一种方便使用eureka的注解而已，可以说使用其他的注册中心后，
 * 都可以使用@EnableDiscoveryClient注解，但是使用@EnableEurekaClient的情景，就是在服务采用eureka作为注册中心的时候，使用场景较为单一。
 */
@EnableFeignClients//表示开启FeignClients，后面会在接口上用到@FeignClient
@EnableCircuitBreaker//服务容错_保护断路器
@EnableEurekaClient//表明自己是一个eurekaclient.此处使用此注解，而不使用下面注解
//@EnableDiscoveryClient//表示向注册中心注册,
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
