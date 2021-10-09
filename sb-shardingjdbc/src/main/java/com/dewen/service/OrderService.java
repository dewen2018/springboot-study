package com.dewen.service;

import com.dewen.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findByOrders();

    int batchAdd(List<Order> orders);

    Order getByOrderId(Integer id);

    int insertOrder(Order order);
}