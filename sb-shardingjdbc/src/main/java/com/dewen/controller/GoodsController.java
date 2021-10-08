package com.dewen.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dewen.entity.Goods;
import com.dewen.entity.Tdict;
import com.dewen.service.GoodsService;
import com.dewen.service.TdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TdictService tdictService;
    @Autowired
    private IdGenerator idGenerator;

    @GetMapping("/list")
    public List<Goods> list() {

        return goodsService.findByGoods();
    }

    @GetMapping("/getGoodsById")
    public Goods getGoodsById(Integer id) {
        for (int i = 0; i < 100000; i++) {
            System.out.println(idGenerator.generateId());
        }
        return goodsService.getByGoodsId(id);
    }

    @GetMapping("/getById")
    public Tdict getById(Integer id) {
        return tdictService.getById(id);
    }

    @GetMapping("/saveTdict")
    public int saveTdict() {
        Tdict entity = new Tdict();
        entity.setId(idGenerator.generateId().intValue());
        entity.setDictName("男");
        entity.setDictValue("man");
        return tdictService.insertTdict(entity);
    }

    @GetMapping("batchAdd")
    public int batchAdd() {
        List<Goods> entitys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods();
            goods.setMoney(new BigDecimal(0.1 * i));
            goods.setCreateTime(new Date());
            goods.setGoodsName("Java编程思想 第" + i + "版");
            goods.setStock(i + 20);
            goods.setId(idGenerator.generateId().intValue());
            entitys.add(goods);
        }
        return goodsService.batchAdd(entitys);
    }
}