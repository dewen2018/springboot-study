package com.dewen.策略模式;

public interface Payment {
    void pay(Long order, double amount);
}