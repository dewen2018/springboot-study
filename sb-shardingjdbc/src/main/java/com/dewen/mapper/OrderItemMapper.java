package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.OrderItem;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<OrderItem> getOrderItems();
}
