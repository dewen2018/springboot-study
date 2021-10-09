package com.dewen.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dewen.entity.Order;
import com.dewen.entity.OrderItem;
import com.dewen.service.OrderItemService;
import com.dewen.service.OrderService;
import com.dewen.utils.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderServiceImpl;
    @Autowired
    private OrderItemService orderItemServiceImpl;
    @Autowired
    private IdGenerator idGenerator;

    @GetMapping("createOrder")
    @Transactional
    public void createOrder() {
        Integer userId = (int) (Math.random() * 3) % 3 + 1;
        Order order = new Order();
        order.setId(SnowFlakeUtil.getUid().intValue());
        order.setUserId(userId);
        order.setOrderNo("mmpOrderNo-" + Math.abs(order.getId()));
        orderServiceImpl.insertOrder(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setId(idGenerator.generateId().intValue());
        orderItem.setOrderId(order.getId());
        orderItem.setGoodName("狗子肉");
        orderItem.setUserId(userId);
        orderItemServiceImpl.insertOrderItem(orderItem);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(idGenerator.generateId().intValue());
        orderItem2.setOrderId(order.getId());
        orderItem2.setGoodName("狗子肉丸");
        orderItem2.setUserId(userId);
        orderItemServiceImpl.insertOrderItem(orderItem2);
    }

    @GetMapping("getOrderItems")
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems = orderItemServiceImpl.getOrderItems();
        return orderItems;
    }
}