package com.dewen.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    // @Bean
    // public Cache<String, Object> caffeineCache() {
    //     return Caffeine.newBuilder()
    //             // 设置最后一次写入或访问后经过固定时间过期
    //             .expireAfterWrite(60, TimeUnit.SECONDS)
    //             // 初始的缓存空间大小
    //             .initialCapacity(100)
    //             // 缓存的最大条数
    //             .maximumSize(1000)
    //             .build();
    // }

    @Primary
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        List<Cache> caches = new ArrayList<>();
        caches.add(new CaffeineCache("test1",
                Caffeine.newBuilder()
                        .expireAfterWrite(100, TimeUnit.SECONDS)
                        .recordStats()
                        .maximumSize(Integer.MAX_VALUE)
                        .removalListener((key, value, cause) -> {
                            System.out.println("");
                        }).build()));

        caches.add(new CaffeineCache("test2",
                Caffeine.newBuilder()
                        .expireAfterWrite(100, TimeUnit.SECONDS)
                        .recordStats()
                        .maximumSize(Integer.MAX_VALUE)
                        .removalListener((key, value, cause) -> {
                            System.out.println("");
                        }).build()));

        SimpleCacheManager scm = new SimpleCacheManager();
        scm.setCaches(caches);
        return scm;
    }


    @Bean("redisCacheManager")
    public CacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存的默认过期时间:180s
                .entryTtl(Duration.ofSeconds(180))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                // 不缓存空值
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }
}