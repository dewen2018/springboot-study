package com.dewen.策略模式;

public class PayExample {
    public Object makeOrder(Long goodsId, String type) {
        // 生成本地的订单
        // Order order = this.orderService.makeOrder(goodsId);
        //选择支付方式
        PayType payType = PayType.getByPayType(type);
        //进行支付
        payType.get().pay(11L, 110.0D);
        return new Object();
    }
}
