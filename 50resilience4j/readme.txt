官网地址
https://resilience4j.readme.io/docs

监控地址
http://dewen.com:8082/node2/actuator


webflux框架不同于mvc，他启动在netty上，请求不会占用线程资源。
返回值分为mono和flux两种。mono只有一个或空的返回。flux有一个或多个返回值。


circuitbeaker（断路器），bulkheader（挡板）,ratelimiter（限流器），retry（重试）


Aspect 顺序
Resilience4j Aspects 顺序如下：
Retry ( CircuitBreaker ( RateLimiter ( TimeLimiter ( Bulkhead ( Function ) ) ) ) )
所以在最后应用 Retry（如果需要）。
如果你需要不同的顺序，你必须使用函数链接样式而不是Spring注释样式，或者使用以下属性显式设置aspect 顺序:

- resilience4j.retry.retryAspectOrder
- resilience4j.circuitbreaker.circuitBreakerAspectOrder
- resilience4j.ratelimiter.rateLimiterAspectOrder
- resilience4j.timelimiter.timeLimiterAspectOrder
- resilience4j.bulkhead.bulkheadAspectOrder

例如 - 要使断路器在重试完成后启动，
您必须将 retryAspectOrder 属性设置为大于 circuitBreakerAspectOrder 值的值（更高的值 = 更高的优先级）。
resilience4j:
  circuitbreaker:
    circuitBreakerAspectOrder: 1
  retry:
    retryAspectOrder: 2
