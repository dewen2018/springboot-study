package com.dewen.service;

import com.dewen.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findByOrderItems();

    int batchAdd(List<OrderItem> orderItems);

    OrderItem getByOrderItemId(Integer id);

    int insertOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItems();
}