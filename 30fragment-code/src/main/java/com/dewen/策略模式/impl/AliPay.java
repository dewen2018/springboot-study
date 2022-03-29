package com.dewen.策略模式.impl;

import com.dewen.策略模式.Payment;

public class AliPay implements Payment {
    @Override
    public void pay(Long order, double amount) {
        System.out.println("----支付宝支付----");
        System.out.println("支付宝支付111元");
    }
}