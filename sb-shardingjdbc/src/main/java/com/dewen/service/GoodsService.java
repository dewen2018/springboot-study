package com.dewen.service;

import com.dewen.entity.Goods;

import java.util.List;

public interface GoodsService {

     List<Goods> findByGoods();

     int batchAdd(List<Goods> goods);

     Goods getByGoodsId(Integer id);

}