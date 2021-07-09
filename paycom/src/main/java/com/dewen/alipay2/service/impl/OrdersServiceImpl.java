package com.dewen.alipay2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.alipay2.idworker.Sid;
import com.dewen.alipay2.mapper.FlowMapper;
import com.dewen.alipay2.mapper.OrdersMapper;
import com.dewen.alipay2.pojo.Flow;
import com.dewen.alipay2.pojo.Orders;
import com.dewen.alipay2.service.OrdersService;
import com.dewen.alipay2.utils.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FlowMapper flowMapper;

    @Autowired
    private Sid sid;

    @Override
    public void saveOrder(Orders order) {
        ordersMapper.insert(order);
    }

    @Override
    public Orders getOrderById(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount) {

        Orders order = getOrderById(orderId);
        if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
            order = new Orders();
            order.setId(orderId);
            order.setOrderStatus(OrderStatusEnum.PAID.key);
            order.setPaidTime(new Date());
            order.setPaidAmount(paidAmount);

            ordersMapper.updateByPrimaryKeySelective(order);

            order = getOrderById(orderId);

            String flowId = sid.nextShort();
            Flow flow = new Flow();
            flow.setId(flowId);
            flow.setFlowNum(alpayFlowNum);
            flow.setBuyCounts(order.getBuyCounts());
            flow.setCreateTime(new Date());
            flow.setOrderNum(orderId);
            flow.setPaidAmount(paidAmount);
            flow.setPaidMethod(1);
            flow.setProductId(order.getProductId());

            flowMapper.insertSelective(flow);
        }

    }

}
