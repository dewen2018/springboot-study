package com.dewen.alipay2.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.alipay2.pojo.Orders;

/**
 * 订单操作 service
 * @author ibm
 *
 */
public interface OrdersService extends IService<Orders> {

	/**
	 * 新增订单
	 * @param order
	 */
	public void saveOrder(Orders order);
	
	/**
	 * 
	 * @Title: OrdersService.java
	 * @Package com.sihai.service
	 * @Description: 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * 
	 * @author sihai
	 * @date 2017年8月23日 下午9:04:35
	 * @version V1.0
	 */
	public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount);
	
	/**
	 * 获取订单
	 * @param orderId
	 * @return
	 */
	public Orders getOrderById(String orderId);
	
}
