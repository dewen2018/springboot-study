package com.dewen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.entity.UserInfo;
import com.dewen.mapper.UserInfoMapper;
import com.dewen.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


@Slf4j
@Service
// @CacheConfig(cacheNames = {"test2"})
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

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
    @Cacheable(value = "dewen", key = "#id", cacheManager = "redisCacheManager", unless = "#result == null")
    public UserInfo getByName(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    @Cacheable(value = "test2", key = "#id", cacheManager = "caffeineCacheManager")
    public UserInfo getByName2(Integer id) {
        return this.baseMapper.selectById(id);
        // 多级缓存：调用redisCacheManager：不生效，猜测是切面没有跳转
        // return this.getByName(id);
    }

    /**
     * 存在问题，
     *
     * @param userInfo
     * @return
     */
    @CachePut(key = "#userInfo.id")
    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        this.baseMapper.updateById(userInfo);
        return userInfo;
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。
     * 其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。
     */
    @Caching(
            cacheable = @Cacheable("user"),
            evict = {
                    @CacheEvict(value = "user1", key = "#id"),
                    @CacheEvict(value = "user", allEntries = true)
            }
    )
    public Integer find(Integer id) {
        return id;
    }
}
