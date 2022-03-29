package com.dewen.策略模式.impl;

import com.dewen.策略模式.Payment;

public class WechatPay implements Payment {
    @Override
    public void pay(Long orderId, double amount) {
        System.out.println("---微信支付---");
        System.out.println("支付222元");
    }
}