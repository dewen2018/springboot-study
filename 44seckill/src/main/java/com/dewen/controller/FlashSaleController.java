package com.dewen.controller;

import com.dewen.annotations.DistriLimitAnno;
import com.dewen.annotations.aspects.LimitAspect;
import com.dewen.config.lock.DistributedLock;
import com.dewen.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class FlashSaleController {

    @Autowired
    OrderService orderService;
    @Autowired
    DistributedLock distributedLock;
    @Autowired
    LimitAspect limitAspect;
    //注意RedisTemplate用的String,String，后续所有用到的key和value都是String的
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final String LOCK_PRE = "LOCK_ORDER";

    /**
     * 初始化订单的方法，即秒杀开始的时候，秒杀接口才会有效，
     * 这个方法可以采用定时任务自动实现也可以
     *
     * @return
     */
    @PostMapping("/initCatalog")
    public String initCatalog() {
        try {
            orderService.initCatalog();
        } catch (Exception e) {
            log.error("error", e);
        }

        return "init is ok";
    }

    @PostMapping("/placeOrder")
    @DistriLimitAnno(limitKey = "limit", limit = 100, seconds = "1")
    public Long placeOrder(Long orderId) {
        Long saleOrderId = 0L;
        boolean locked = false;
        String key = LOCK_PRE + orderId;
        String uuid = String.valueOf(orderId);
        try {
            locked = distributedLock.distributedLock(key, uuid, "10");
            if (locked) {
                //直接操作数据库
//                saleOrderId = orderService.placeOrder(orderId);
                //操作缓存 异步操作数据库
                saleOrderId = orderService.placeOrderWithQueue(orderId);
            }
            log.info("saleOrderId:{}", saleOrderId);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (locked) {
                distributedLock.distributedUnlock(key, uuid);
            }
        }
        return saleOrderId;
    }

}
