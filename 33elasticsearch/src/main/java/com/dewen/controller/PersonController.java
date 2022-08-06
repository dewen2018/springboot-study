package com.dewen.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/person")
@Api(tags = "person index")
@ApiSort(3)
public class PersonController {
    @Autowired
    private RestHighLevelClient client;

    private SearchResponse commonQuery(QueryBuilder query) throws IOException {
        return this.commonQuery(query, null, null);
    }

    private SearchResponse commonQuery(QueryBuilder query, Integer size) throws IOException {
        return this.commonQuery(query, null, size);
    }

    private SearchResponse commonQuery(AggregationBuilder aggregationBuilder) throws IOException {
        return this.commonQuery(null, aggregationBuilder, null);
    }

    private SearchResponse commonQuery(AggregationBuilder aggregationBuilder, Integer size) throws IOException {
        return this.commonQuery(null, aggregationBuilder, size);
    }

    private SearchResponse commonQuery(QueryBuilder query, AggregationBuilder aggregationBuilder) throws IOException {
        return this.commonQuery(query, aggregationBuilder, null);
    }

    /**
     * @param query              QueryBuilder
     * @param aggregationBuilder AggregationBuilder
     * @param size               size
     * @return
     * @throws IOException
     */
    private SearchResponse commonQuery(QueryBuilder query, AggregationBuilder aggregationBuilder, Integer size) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (size != null) {
            searchSourceBuilder.size(size);
        }
        if (query != null) {
            // 构建查询语句
            searchSourceBuilder.query(query);
        }
        if (aggregationBuilder != null) {
            // 将聚合查询条件构建到SearchSourceBuilder中
            searchSourceBuilder.aggregation(aggregationBuilder);
        }
        return this.commonQuery(searchSourceBuilder);
    }

    // common base
    private SearchResponse commonQuery(SearchSourceBuilder searchSourceBuilder) throws IOException {
        // 根据索引创建查询请求
        SearchRequest searchRequest = new SearchRequest("person");
        searchRequest.source(searchSourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    // ElasticSearch 5.0以后，string类型有重大变更，移除了string类型，string字段被拆分成两种新的数据类型: text用于全文搜索的，而keyword用于关键词搜索。
    @ApiOperation(value = "term精确查询", notes = "类似Mysql里的=查询", httpMethod = "GET")
    @GetMapping(value = "/queryTerm")
    public SearchResponse queryTerm() throws IOException {
        // 构造query
        QueryBuilder query =
                // 有评分，会有性能损耗
                // QueryBuilders.termQuery("name.keyword", "张无忌");
                // 关闭打分,减少性能损耗
                QueryBuilders.constantScoreQuery(
                        QueryBuilders.termQuery("sect.keyword", "峨嵋派")
                );
        return this.commonQuery(query);
    }

    @ApiOperation(value = "多值查询-terms", notes = "类似Mysql里的IN查询", httpMethod = "GET")
    @GetMapping(value = "/queryMultiTerm")
    public SearchResponse queryMultiTerm() throws IOException {
        // 构造query
        QueryBuilder query =
                // 有评分，会有性能损耗
                // QueryBuilders.termsQuery("sect.keyword", Arrays.asList("明教", "武当派"));
                // 关闭打分,减少性能损耗
                QueryBuilders.constantScoreQuery(
                        QueryBuilders.termsQuery("sect.keyword", Arrays.asList("明教", "峨嵋派"))
                );
        return this.commonQuery(query);
    }

    @ApiOperation(value = "范围查询-range", notes = "类似Mysql里的between查询", httpMethod = "GET")
    @GetMapping(value = "/rangeQuery")
    public SearchResponse rangeQuery() throws IOException {
        return this.commonQuery(
                QueryBuilders.rangeQuery("age").gte(10).lte(30)
        );
    }

    @ApiOperation(value = "前缀查询-prefix", notes = "右like", httpMethod = "GET")
    @GetMapping(value = "/prefixQuery")
    public SearchResponse prefixQuery() throws IOException {
        return this.commonQuery(
                QueryBuilders.prefixQuery("sect.keyword", "武当")
        );
    }

    @ApiOperation(value = "通配符查询-wildcard", notes = "张%忌", httpMethod = "GET")
    @GetMapping(value = "/wildcardQuery")
    public SearchResponse wildcardQuery() throws IOException {
        return this.commonQuery(
                QueryBuilders.wildcardQuery("sect.keyword", "张*忌")
        );
    }

    @ApiOperation(value = "复合查询", notes = "and", httpMethod = "GET")
    @GetMapping(value = "/boolQuery")
    public SearchResponse boolQuery() throws IOException {
        return this.commonQuery(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("sex", "男"))
                        .must(QueryBuilders.termQuery("sect.keyword", "明教"))
        );
    }

    /**
     * must：所有的语句都必须匹配，与 ‘=’ 等价。
     * must_not：所有的语句都不能匹配，与 ‘!=’ 或 not in 等价。
     * should：至少有n个语句要匹配，n由参数控制。
     */
    @ApiOperation(value = "布尔查询", notes = "", httpMethod = "GET")
    @GetMapping(value = "/boolQuery2")
    public SearchResponse boolQuery2() throws IOException {
        return this.commonQuery(
                QueryBuilders.boolQuery()
                        // 必须是男性
                        .must(QueryBuilders.termQuery("sex", "男"))
                        // .must(QueryBuilders.rangeQuery("age").gte(30).lte(40))
                        // 一定不能是张*忌
                        // .mustNot(QueryBuilders.wildcardQuery("name.keyword", "张*忌"))
                        // 设置should至少需要满足几个条件
                        .should(QueryBuilders.termQuery("address.word", "峨眉山"))
                        .should(QueryBuilders.termQuery("sect.keyword", "明教"))
                        .should(QueryBuilders.rangeQuery("power.keyword").gte(10).lte(40))
                        .minimumShouldMatch(1)
        );
    }

    /**
     * query和filter的区别：
     * query查询的时候，会先比较查询条件，然后计算分值，最后返回文档结果；
     * filter是先判断是否满足查询条件，如果不满足会缓存查询结果（记录该文档不满足结果），满足的话，
     * 就直接缓存结果，filter不会对结果进行评分，能够提高查询效率。
     */
    @ApiOperation(value = "Filter查询", notes = "", httpMethod = "GET")
    @GetMapping(value = "/filterQuery")
    public SearchResponse filterQuery() throws IOException {
        return this.commonQuery(
                QueryBuilders.boolQuery()
                        // 单独使用：
                        .filter(QueryBuilders.termQuery("sex", "男"))
        );
    }

    /**
     * 先must，再filter
     * select * from (select * from persons where sect = '明教')) a where sex = '女';
     */
    @ApiOperation(value = "Filter查询2", notes = "和must、must_not同级，相当于子查询", httpMethod = "GET")
    @GetMapping(value = "/filterQuery2")
    public SearchResponse filterQuery2() throws IOException {
        return this.commonQuery(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("sect.keyword", "明教"))
                        .filter(QueryBuilders.termQuery("sex", "男"))
        );
    }

    @ApiOperation(value = "Filter查询3", notes = "将must、must_not置于filter下，这种方式是最常用的", httpMethod = "GET")
    @GetMapping(value = "/filterQuery3")
    public SearchResponse filterQuery3() throws IOException {
        return this.commonQuery(
                QueryBuilders.boolQuery()
                        .filter(
                                QueryBuilders.boolQuery()
                                        .must(QueryBuilders.termQuery("sect.keyword", "明教"))
                                        .must(QueryBuilders.rangeQuery("age").gte(20).lte(35))
                                        .mustNot(QueryBuilders.termQuery("sex.keyword", "女"))
                        )
        );
    }


    @ApiOperation(value = "聚合查询maxQuery", notes = "最值、平均值、求和", httpMethod = "GET")
    @GetMapping(value = "/maxQuery")
    public SearchResponse maxQuery() throws IOException {
        // 聚合查询条件
        AggregationBuilder aggBuilder =
                // AggregationBuilders.max("max_age").field("age");
                AggregationBuilders.min("min_age").field("age");
        // AggregationBuilders.avg("min_age").field("age");
        // AggregationBuilders.sum("min_age").field("age");
        // AggregationBuilders.count("min_age").field("age");
        return this.commonQuery(aggBuilder, 1);
    }

    @ApiOperation(value = "聚合查询cardinalityQuery", notes = "去重查询", httpMethod = "GET")
    @GetMapping(value = "/cardinalityQuery")
    public SearchResponse cardinalityQuery() throws IOException {
        // 去重查询条件
        AggregationBuilder aggBuilder = AggregationBuilders.cardinality("sect_count").field("sect.keyword");
        return this.commonQuery(aggBuilder, 0);
    }

    @ApiOperation(value = "分组聚合groupQuery", notes = "单条件分组", httpMethod = "GET")
    @GetMapping(value = "/groupQuery")
    public SearchResponse groupQuery() throws IOException {
        // 单条件分组
        AggregationBuilder aggBuilder = AggregationBuilders.terms("sect_count").field("sect.keyword");
        return this.commonQuery(aggBuilder, 0);
    }

    @ApiOperation(value = "分组聚合groupQuery2", notes = "多条件分组", httpMethod = "GET")
    @GetMapping(value = "/groupQuery2")
    // select sect,sex,count(id) from persons group by sect,sex;
    public SearchResponse groupQuery2() throws IOException {
        // 多条件分组
        AggregationBuilder aggBuilder = AggregationBuilders.terms("sect_count").field("sect.keyword").size(10)
                .subAggregation(
                        AggregationBuilders.terms("sex_count").field("sex.keyword").size(10)
                );
        return this.commonQuery(aggBuilder, 0);
    }


    @ApiOperation(value = "过滤聚合", notes = "过滤聚合", httpMethod = "GET")
    @GetMapping(value = "/queryAgg")
    // select max(age) from mytest.persons where sect = '明教';
    public SearchResponse queryAgg() throws IOException {
        // 多条件分组
        AggregationBuilder aggBuilder = AggregationBuilders.max("max_age").field("age");
        QueryBuilder query = QueryBuilders.termQuery("sect.keyword", "明教");
        return this.commonQuery(query, aggBuilder, 0);
    }


    /**
     * select
     * sum(case when age<=20 then 1 else 0 end) ageGroup1,
     * sum(case when age >20 and age <=40 then 1 else 0 end) ageGroup2,
     * sum(case when age >40 and age <=60 then 1 else 0 end) ageGroup3,
     * sum(case when age >60 and age <=200 then 1 else 0 end) ageGroup4
     * from
     * persons;
     */
    @ApiOperation(value = "complexQuery", notes = "from..to", httpMethod = "GET")
    @GetMapping(value = "/complexQuery")
    public SearchResponse complexQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //制定检索条件
        // RangeAggregationBuilder rangeAggregationBuilder = AggregationBuilders.range("price_range")
        //         .field("age")
        //         .addRange(0, 20)
        //         .addRange(20, 40)
        //         .addRange(40, 60)
        //         .addRange(40, 200);

        RangeAggregationBuilder rangeAggregationBuilder = AggregationBuilders.range("person_cnt")
                .field("age")
                .addRange(0, 20)
                .addRange(20, 40)
                .addRange(40, 60)
                .addRange(40, 200);

        searchSourceBuilder.aggregation(rangeAggregationBuilder)
                .size(0);
        return this.commonQuery(searchSourceBuilder);
    }
}
