//package com.dewen.lock;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class sinLock {
//    // 通过消息提前初始化好，借助ConcurrentHashMap实现高效线程安全
//    private static ConcurrentHashMap<Long, Boolean> SECKILL_FLAG_MAP = new ConcurrentHashMap<>();
//    // 通过消息提前设置好。由于AtomicInteger本身具备原子性，因此这里可以直接使用HashMap
//    private static Map<Long, AtomicInteger> SECKILL_STOCK_MAP = new HashMap<>();
//
//
//    public SeckillActivityRequestVO seckillHandle(SeckillActivityRequestVO request) {
//        SeckillActivityRequestVO response;
//
//        Long seckillId = request.getSeckillId();
//        if(!SECKILL_FLAG_MAP.get(requestseckillId)) {
//            // 业务异常
//        }
//        // 用户活动校验
//        // 库存校验
//        if(SECKILL_STOCK_MAP.get(seckillId).decrementAndGet() < 0) {
//            SECKILL_FLAG_MAP.put(seckillId, false);
//            // 业务异常
//        }
//        // 生成订单
//        // 发布订单创建成功事件
//        // 构建响应
//        return response;
//    }
//}
