发布订阅模式在两个角色中间是一个中间角色来过渡的，
发布者并不直接与订阅者产生交互。

中间过渡区域对应的就是是缓冲区。因为这个缓冲区的存在，发布者与订阅者的工作就可以实现更大程度的解耦。发布者不会因为订阅者处理速度慢，
而影响自己的发布任务，它只需要快速生产即可。而订阅者也不用太担心一时来不及处理，因为有缓冲区在，
可以一点点排队来完成（也就是我们常说的“削峰填谷”效果）。
RabbitMQ、Kafka、RocketMQ这些中间件的本质其实就是实现发布订阅模式中的这个中间缓冲区。
而Redis也提供了简单的发布订阅实现，一些简单需求的时候，可以一用！