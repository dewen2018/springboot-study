package com.dewen.service;

import com.dewen.entity.TaskDetail;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;


public class MsService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 分布式锁
     *
     * @param taskId
     * @return
     */
    public boolean taskScoreOperat(Integer taskId) {
        String key = "dec_store_lock_" + taskId;
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            lock.lock();
            // 查找数据库
            TaskDetail taskDetail = new TaskDetail();
            //如果还有得分
            if (taskDetail.getScore() == 0) {
                return false;
            }
            //简单减库存操作 没有重新写其他接口了
            taskDetail.setScore(taskDetail.getScore() - 1);
            // 更新操作
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
        return true;
    }

    /**
     * synchronized
     * 单体服务没有问题，但是多个服务就会有问题
     *
     * @param taskId
     * @return
     */
    public synchronized boolean taskScoreOperat2(Integer taskId) {
        try {
            // 查找数据库
            TaskDetail taskDetail = new TaskDetail();
            //如果还有得分
            if (taskDetail.getScore() == 0) {
                return false;
            }
            //简单减库存操作 没有重新写其他接口了
            taskDetail.setScore(taskDetail.getScore() - 1);
            // 更新操作
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * 不加锁
     *
     * @param taskId
     * @return
     */
    public boolean taskScoreOperat3(Integer taskId) {
        try {
            // 查找数据库
            TaskDetail taskDetail = new TaskDetail();
            //如果还有得分
            if (taskDetail.getScore() == 0) {
                return false;
            }
            //简单减库存操作 没有重新写其他接口了
            taskDetail.setScore(taskDetail.getScore() - 1);
            // 更新操作
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
