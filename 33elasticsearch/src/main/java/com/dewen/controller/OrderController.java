package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.dewen.entity.Order;
import com.dewen.repository.OrderRepository;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "order index")
@ApiSupport(order = 5)
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private OrderRepository orderRepository;

    @ApiOperation(value = "创建索引")
    @ApiOperationSupport(order = 1)
    @GetMapping("create")
    public String create(@RequestParam String indexName) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        if (indexOperations.exists()) {
            return "索引已存在";
        }
        indexOperations.create();
        return "索引创建成功";
    }

    @ApiOperation(value = "删除索引")
    @ApiOperationSupport(order = 1)
    @GetMapping("delete")
    public String delete(@RequestParam String indexName) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        indexOperations.delete();
        return "索引删除成功";
    }

    /**
     * 批量创建
     */
    @PostMapping("saveBatch")
    public String saveBatch(@RequestBody List<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return "文档不能为空";
        }
        orderRepository.saveAll(orders);
        return "保存成功";
    }

    /**
     * 根据id删除
     */
    @GetMapping("deleteById")
    public String deleteById(@RequestParam Integer id) {
        orderRepository.deleteById(id);
        return "删除成功";
    }

    /**
     * 根据id更新
     */
    @PostMapping("updateById")
    public String updateById(@RequestBody Order order) {
        orderRepository.save(order);
        ;
        return "更新成功";
    }

    /**
     * 根据id搜索
     */
    @GetMapping("findById")
    public String findById(@RequestParam Integer id) {
        return JSON.toJSONString(orderRepository.findById(id));
    }

    /**
     * 分页搜索所有
     */
    @GetMapping("findAll")
    public Page<Order> findAll(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<Order> page = orderRepository.findAll(PageRequest.of(pageIndex, pageSize));

        return page;
    }

    /**
     * 条件分页搜索
     */
    @GetMapping("findList")
    public List<Order> findList(@RequestBody Order order, @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("orderDesc").contains(order.getOrderDesc()))
                .and(new Criteria("orderNo").is(order.getOrderNo())))
                .setPageable(PageRequest.of(pageIndex, pageSize));

        SearchHits<Order> searchHits = elasticsearchRestTemplate.search(criteriaQuery, Order.class);
        List<Order> result = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return result;
    }

    /**
     * 条件高亮分页搜索
     */
    @GetMapping("findHighlight")
    public List<Order> findHighlight(@RequestBody(required = false) Order order, @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        if (order == null) {
            return null;
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("orderNo").is(order.getOrderNo()))
                .and(new Criteria("orderDesc").contains(order.getOrderDesc())))
                .setPageable(PageRequest.of(pageIndex, pageSize));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("orderNo").field("orderDesc");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<h3 style=\"color:blue\">");
        highlightBuilder.postTags("</h3>");

        HighlightQuery highlightQuery = new HighlightQuery(highlightBuilder);
        criteriaQuery.setHighlightQuery(highlightQuery);

        SearchHits<Order> searchHits = elasticsearchRestTemplate.search(criteriaQuery, Order.class);

        List<Order> result = searchHits.get().map(e -> {
            Order element = e.getContent();
            element.setHighlights(e.getHighlightFields());
            return element;
        }).collect(Collectors.toList());

        return result;
    }
}