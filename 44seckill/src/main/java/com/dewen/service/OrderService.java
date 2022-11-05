package com.dewen.service;

public interface OrderService {

    void initCatalog();

    Long placeOrder(Long catalogId);

    Long placeOrderWithQueue(Long catalogId);

}
