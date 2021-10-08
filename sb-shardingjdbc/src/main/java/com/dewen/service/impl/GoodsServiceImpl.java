package com.dewen.service.impl;

import com.dewen.entity.Goods;
import com.dewen.mapper.GoodsMapper;
import com.dewen.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> findByGoods() {
        return mapper.selectList(null);
    }

    @Override
    public int batchAdd(List<Goods> goods) {
        int i = 0;
        for (Goods g : goods)
            i += mapper.insert(g);
        return i;
    }

    @Override
    public Goods getByGoodsId(Integer id) {
        return mapper.selectById(id);
    }
}