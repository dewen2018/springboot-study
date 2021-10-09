package com.dewen.service.impl;

import com.dewen.entity.OrderItem;
import com.dewen.mapper.OrderItemMapper;
import com.dewen.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> findByOrderItems() {
        return orderItemMapper.selectList(null);
    }

    @Override
    public int batchAdd(List<OrderItem> orderItems) {
        return 0;
    }

    @Override
    public OrderItem getByOrderItemId(Integer id) {
        return orderItemMapper.selectById(id);
    }

    @Override
    public int insertOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItemMapper.getOrderItems();
    }
}
