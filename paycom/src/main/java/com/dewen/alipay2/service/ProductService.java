package com.dewen.alipay2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.alipay2.pojo.Product;

import java.util.List;


public interface ProductService extends IService<Product> {

	/**
	 * 获取所有产品列表
	 * @return
	 */
	public List<Product> getProducts();
	
	/**
	 * 根据产品ID查询产品详情
	 * @param productId
	 * @return
	 */
	public Product getProductById(String productId);
}
