package com.dewen.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonAutoConfiguration {

    @Value("${redisson.address}")
    private String addressUrl;
    @Value("${redisson.password}")
    private String password;

    /**
     * 单机模式配置
     **/
    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(addressUrl)
                .setPassword(password)
                .setDatabase(0)
                .setReconnectionTimeout(10000)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     * sentinel模式，实现代码和单机模式几乎一样，唯一的不同就是Config的构造
     //     */
//    @Bean
//    public RedissonClient getRedisson() {
//        Config config = new Config();
//        config.useSentinelServers()
//                //可以多个redis
//                .addSentinelAddress(addressUrl)
//                //主节点
//                .setMasterName("MasterNode")
//                .setPassword(password)
//                .setDatabase(0)
//                .setReconnectionTimeout(10000)
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);
//        return Redisson.create(config);
//    }

    /**
     * 集群模式
     */
//    @Bean
//    public RedissonClient getRedisson() {
//        Config config = new Config();
//        config.useClusterServers()
//                //多个redis
//                .addNodeAddress(addressUrl)
//                //主节点
//                .setPassword(password)
//                .setScanInterval(5000);
//        return Redisson.create(config);
//    }

    /**
     * 主从模式
     *
     * @return
     * @Author huangwb
     * @Description //
     * @Date 20203/19 10:54
     * @Param
     **/
//    @Bean
//    public RedissonClient getRedisson() {
//        RedissonClient redisson;
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                //可以用"rediss://"来启用SSL连接
//                .setMasterAddress("redis://***(主服务器IP):6379").setPassword("web2017")
//                .addSlaveAddress("redis://***（从服务器IP）:6379")
//                .setReconnectionTimeout(10000)
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);//（连接超时，单位：毫秒 默认值：3000）;
//        redisson = Redisson.create(config);
//        return redisson;
//    }
}