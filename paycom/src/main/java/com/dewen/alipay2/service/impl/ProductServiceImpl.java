package com.dewen.alipay2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dewen.alipay2.mapper.ProductMapper;
import com.dewen.alipay2.pojo.Product;
import com.dewen.alipay2.pojo.ProductExample;
import com.dewen.alipay2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProducts() {

        ProductExample pe = new ProductExample();
//		Criteria pc = pe.createCriteria();
        List<Product> list = productMapper.selectByExample(pe);

        return list;
    }

    @Override
    public Product getProductById(String productId) {

        return productMapper.selectByPrimaryKey(productId);
    }

}
