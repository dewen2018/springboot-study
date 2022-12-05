package com.dewen.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.entity.UserInfo;
import com.dewen.mapper.UserInfoMapper;
import com.dewen.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    /**
     * expire 超时时间
     * cacheType 缓存类型
     * localLimit 限制缓存数量
     */
    @CreateCache(expire = 100, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<String, String> cache;

    public void m1() {
        //写入缓存
        cache.put("abc", "aaa");
        //读取缓存
        System.out.println("1 > " + cache.get("abc"));

        System.out.println("2 > " + cache.computeIfAbsent("abc1", res -> {
            return "111";
        }));

        System.out.println("3 > " + cache.computeIfAbsent("abc2", res -> {
            return "222";
        }, false, 10, TimeUnit.SECONDS));
    }


    /**
     * 此时没有id，故没有缓存
     *
     * @param userInfo
     */
    // @CachePut(key = "#userInfo.id")
    @Override
    public void addUserInfo(UserInfo userInfo) {
        this.baseMapper.insert(userInfo);
    }

    @Override
    @Cached(name = "dewen:", cacheType = CacheType.LOCAL, key = "#id", expire = 30, timeUnit = TimeUnit.SECONDS)
    public UserInfo getByName(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    @Cached(name = "dewen:", cacheType = CacheType.REMOTE, key = "#id", expire = 30, timeUnit = TimeUnit.SECONDS)
    public UserInfo getByName2(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * refresh : 刷新间隔
     * stopRefreshAfterLastAccess : 指定该key多长时间没有访问就停止刷新，如果不指定会一直刷新
     * timeUnit :时间单位
     * refreshLockTimeout : 类型为BOTH/REMOTE的缓存刷新时，同时只会有一台服务器在刷新，这台服务器会在远程缓存放置一个分布式锁，此配置指定该锁的超时时间
     *
     * @param id
     * @return
     * @CachePenetrationProtect : 同步加载数据，当缓存访问未命中的情况下，对并发进行的加载行为进行保护。 当前版本实现的是单JVM内的保护，即同一个JVM中同一个key只有一个线程去加载，其它线程等待结果。
     */
    @Override
    @Cached(name = "dewen:", cacheType = CacheType.BOTH, key = "#id", expire = 60, timeUnit = TimeUnit.SECONDS)
    @CacheRefresh(refresh = 2, stopRefreshAfterLastAccess = 10, timeUnit = TimeUnit.MINUTES)
    @CachePenetrationProtect
    public UserInfo getByName3(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @CacheUpdate(name = "bxcCache:", key = "#id", value = "#result")
    public String updateCache(String id) {
        return "update";
    }

    @CacheInvalidate(name = "bxcCache:", key = "#id")
    public String deleteCache(String id) {
        return "delete";
    }


    // /**
    //  * 存在问题，
    //  *
    //  * @param userInfo
    //  * @return
    //  */
    // @CachePut(key = "#userInfo.id")
    // @Override
    // public UserInfo updateUserInfo(UserInfo userInfo) {
    //     this.baseMapper.updateById(userInfo);
    //     return userInfo;
    // }
    //
    // @CacheEvict(key = "#id")
    // @Override
    // public void deleteById(Integer id) {
    //     this.baseMapper.deleteById(id);
    // }
    //
    // /**
    //  * @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。
    //  * 其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。
    //  */
    // @Caching(
    //         cacheable = @Cacheable("user"),
    //         evict = {
    //                 @CacheEvict(value = "user1", key = "#id"),
    //                 @CacheEvict(value = "user", allEntries = true)
    //         }
    // )
    // public Integer find(Integer id) {
    //     return id;
    // }
}
