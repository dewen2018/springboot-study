package com.dewen.service.impl;

import com.dewen.entity.Order;
import com.dewen.mapper.OrderMapper;
import com.dewen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findByOrders() {
        return orderMapper.selectList(null);
    }

    @Override
    public int batchAdd(List<Order> orders) {
        return 0;
    }

    @Override
    public Order getByOrderId(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }
}
