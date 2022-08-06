package com.dewen.repository;

import com.dewen.entity.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, Integer> {
}