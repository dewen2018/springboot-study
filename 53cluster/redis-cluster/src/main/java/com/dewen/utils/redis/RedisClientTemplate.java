package com.dewen.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author: dsj
 * 进行set  get
 */
@Service
public class RedisClientTemplate {
    private static final Logger log= LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private JedisClusterConfig jedisClusterConfig;

    public boolean setToRedis(String key,Object value){
        try {
        String str=jedisClusterConfig.getJedisCluster().set(key, String.valueOf(value));
        if("OK".equals(str))
            return true;
        }catch (Exception ex){
            log.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
        }
        return false;
    }

    public Object getRedis(String key){
        String str=null;
        try {
             str=jedisClusterConfig.getJedisCluster().get(key);
        }catch (Exception ex){
            log.error("getRedis:{Key:"+key+"}",ex);
        }
        return str;
    }
    
}