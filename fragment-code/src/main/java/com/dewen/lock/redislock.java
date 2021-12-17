//package com.dewen.lock;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//public class redislock {
//    public SeckillActivityRequestVO seckillHandle(SeckillActivityRequestVO request) {
//        SeckillActivityRequestVO response;
//        String key = "key:" + request.getSeckillId();
//        String val = UUID.randomUUID().toString();
//        try {
//            Boolean lockFlag = distributedLocker.lock(key, val, 10, TimeUnit.SECONDS);
//            if (!lockFlag) {
//                // 业务异常
//            }
//
//            // 用户活动校验
//            // 库存校验，基于redis本身的原子性来保证
//            Long currStock = stringRedisTemplate.opsForHash().increment(key + ":info", "stock", -1);
//            if (currStock < 0) { // 说明库存已经扣减完了。
//                // 业务异常。
//                log.error("[抢购下单] 无库存");
//            } else {
//                // 生成订单
//                // 发布订单创建成功事件
//                // 构建响应
//            }
//        } finally {
//            distributedLocker.safedUnLock(key, val);
//            // 构建响应
//        }
//        return response;
//    }
//}
