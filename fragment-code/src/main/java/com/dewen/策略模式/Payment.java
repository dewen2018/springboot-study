package com.dewen.策略模式;

public interface Payment {
    public void pay(Long order, double amount);
}