package com.dewen.策略模式;

import com.dewen.策略模式.impl.AliPay;
import com.dewen.策略模式.impl.WechatPay;

public enum PayType {
    //支付宝        AliPay 是实现类
    ALI_PAY("1", new AliPay()),
    //微信
    WECHAT_PAY("2", new WechatPay());

    private String payType;
    // 这是一个接口
    private Payment payment;

    PayType(String payType, Payment payment) {
        this.payment = payment;
        this.payType = payType;
    }

    //通过get方法获取支付方式
    public Payment get() {
        return this.payment;
    }

    public static PayType getByCode(String payType) {
        for (PayType e : PayType.values()) {
            if (e.payType.equals(payType)) {
                return e;
            }
        }
        return null;
    }
}