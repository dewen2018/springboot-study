package com.dewen.service.impl;

import com.dewen.entity.City;
import com.dewen.repository.CityRepository;
import com.dewen.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public String saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> findByName(String name) {
        return cityRepository.findByCitynameIgnoreCase(name);
    }

    @Override
    public Page<City> findByPage(Pageable page) {
        return cityRepository.findAll(page);
    }

    @Override
    public City findById(String id) {
        return cityRepository.queryCityById(id);
    }

    @Override
    public List<City> findByCitynameOrProvinceid(String cityname, Long provinceid) {
        return cityRepository.findByCitynameOrProvinceid(cityname,provinceid);
    }

    @Override
    public Long deleteByCityname(String cityName) {
        return cityRepository.deleteByCityname(cityName);
    }

    @Override
    public Long deleteById(String id) {
        return cityRepository.deleteById(id);
    }

//     @Override
//     public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
//         // 分页参数
//
//         Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
//         Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
//
//
//         /**
//          * spring-boot-starter-data-elasticsearch
//          * 老版本权重查询方法  例如2.x
//          */
//         // Function Score Query
// //        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
// //                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
// //                    ScoreFunctionBuilders.weightFactorFunction(1000))
// //                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
// //                        ScoreFunctionBuilders.weightFactorFunction(100));
//
//         /**
//          * spring-boot-starter-data-elasticsearch
//          * 较新版本权重查询方法  5.x以上
//          */
//         // 权重查询
//         List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
//         filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("cityname", searchContent), ScoreFunctionBuilders.weightFactorFunction(1000)));
//         filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("description", searchContent), ScoreFunctionBuilders.weightFactorFunction(100)));
//         FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
//         filterFunctionBuilders.toArray(builders);
//         FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(2);
//         /**
//          * 因为新版本的Elasticsearch添加方法已删除，所有过滤器和函数必须作为构造函数参数提供，
//          * 方法是创建一个FunctionScoreQueryBuilder.FilterFunctionBuilder对象数组，每个过滤器/函数对包含一个元素。
//          */
//         // 创建搜索 DSL 查询
//         SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();
//
//         LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());
//
//         Page<City> searchPageResults = cityRepository.search(searchQuery);
//         return searchPageResults.getContent();
//     }


    /**
     * 单条件查询
     */
    // public void a() {
    //     QueryBuilder queryBuilder = QueryBuilders.matchQuery("provinceid", "410400");
    //     Iterable<City> searchPageResults = cityRepository.search(queryBuilder);
    //     Iterator<City> iterator = searchPageResults.iterator();
    //     while (iterator.hasNext()) {
    //         System.out.println(iterator.next());
    //     }
    //     System.out.println("query over");
    // }

    /**
     * 多条件查询：非聚合查询
     * must 相当于and
     * mustnot 相当于not
     * shoud 相当于or
     */
//     public void b() {
//         BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//         //设置模糊搜索，含有珠字
// //        queryBuilder.must(QueryBuilders.fuzzyQuery("cityname", "珠"));
//         //含有关键字
// //        queryBuilder.must(new QueryStringQueryBuilder("市").field("cityname"));
//         FieldSortBuilder sortBuilder = SortBuilders.fieldSort("provinceid").order(SortOrder.DESC);
// //        queryBuilder.must(QueryBuilders.matchQuery("provinceid", "410400"));
// //        queryBuilder.must(QueryBuilders.matchQuery("cityname", "市"));
//         //分页
//         PageRequest pageRequest = new PageRequest(0, 10);
//         NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//         nativeSearchQueryBuilder.withQuery(queryBuilder);
//         nativeSearchQueryBuilder.withPageable(pageRequest);
//         nativeSearchQueryBuilder.withSort(sortBuilder);
//         NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
//         Page<City> cityPage = cityRepository.search(nativeSearchQuery);
//         long total = cityPage.getTotalElements();
//         System.out.println(total);
//         List<City> cityList = cityPage.getContent();
//         for (City city : cityList) {
//             System.out.println(city);
//         }
//
//         //List<City> cityList = elasticsearchTemplate.queryForList(nativeSearchQuery,City.class);
//
// //        Iterable<City> searchPageResults = cityRepository.search(queryBuilder);
// //        Iterator<City> iterator = searchPageResults.iterator();
// //        while (iterator.hasNext()) {
// //            System.out.println(iterator.next());
// //        }
//         System.err.println("query over");
//     }

    /**
     * 聚合操作
     */
//     public void c() {
// //        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//         NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
// //        nativeSearchQueryBuilder.withQuery(matchAllQuery);
//         //nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
//         //nativeSearchQueryBuilder.withIndices("cityindex").withTypes("city");
// //        SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum("sum_avg").field("provinceid");
//         TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("provinceid_terms").field("provinceid");
//         nativeSearchQueryBuilder.addAggregation(termsAggregationBuilder);
//         NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
//         //方式一
// //        Page<City> cityPage = cityRepository.search(nativeSearchQuery);
// //        for (City city : cityPage) {
// //            System.out.println(city);
// //        }
// //        //方式二
// //        List<City> cityList = elasticsearchTemplate.queryForList(nativeSearchQuery, City.class);
// //        for (City city : cityList) {
// //            System.out.println(city);
// //        }
// //        //方式三
// //        Aggregations aggregations = elasticsearchTemplate.query(nativeSearchQuery, new ResultsExtractor<Aggregations>() {
// //            @Override
// //            public Aggregations extract(SearchResponse searchResponse) {
// //                return searchResponse.getAggregations();
// //            }
// //        });
// //        Map<String, Aggregation> aggregationMap = aggregations.asMap();
// //        StringTerms stringTerms = (StringTerms) aggregationMap.get("provinceid_terms");
// //        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
// //        Iterator<StringTerms.Bucket> iterator = buckets.iterator();
// //        while (iterator.hasNext()){
// //            String provinceid = iterator.next().getKeyAsString();
// //            System.out.println(provinceid);
// //        }
//     }

    /**
     * QueryBuild的构建方式
     * 精确查询
     */
//     private void d() {
//         //不分词查询 参数1： 字段名，参数2：字段查询值，因为不分词，所以汉字只能查询【一个字】，英语是一个单词.
//         //单个匹配
// //        QueryBuilder queryBuilder = QueryBuilders.termQuery("cityname", "海");
//         //多个匹配
// //        QueryBuilder queryBuilder = QueryBuilders.termsQuery("cityname", "海", "州");
//         //分词查询：采用默认分词器
//         QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("海郑州", "cityname", "description");
//         //匹配所有：相当于没有设置查询条件
// //        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//         Iterable<City> searchPageResults = cityRepository.search(queryBuilder);
//         Iterator<City> iterator = searchPageResults.iterator();
//         while (iterator.hasNext()) {
//             System.out.println(iterator.next());
//         }
//         System.out.println("query over");
//     }

    /**
     * 范围查询
     */
    // public void e() {
    //     //闭区间查询
    //     //QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").from("420440").to("450400");
    //     //开区间查询:默认是true，也就是包含
    //     //QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").from("410400").to("440400").includeLower(false).includeUpper(false);
    //     //大于
    //     //QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").gt("410400");
    //     //大于等于
    //     //QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").gte("420400");
    //     //小于
    //     QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").lt("420400");
    //     //小于等于
    //     //QueryBuilder queryBuilder = QueryBuilders.rangeQuery("provinceid").lte("420400");
    //     Iterable<City> searchPageResults = cityRepository.search(queryBuilder);
    //     Iterator<City> iterator = searchPageResults.iterator();
    //     while (iterator.hasNext()) {
    //         System.out.println(iterator.next());
    //     }
    //     System.err.println("query over");
    // }

    /**
     * 聚合查询
     * SELECT COUNT(color) FROM table GROUP BY color
     * count(color) 相当于一个指标
     * GROUP BY color 相当于一个桶
     */
//     public void f() {
//         //1.统计某个字段的数量
//         ValueCountAggregationBuilder vcab = AggregationBuilders.count("count_provinceid").field("provinceid");
//         //2.去重统计某个字段的数量（有少量误差）
//         CardinalityAggregationBuilder cardinalityAggregationBuilder = AggregationBuilders.cardinality("distinct_count_provinceid").field("provinceid");
//         //3.聚合过滤
//         FilterAggregationBuilder filterAggregationBuilder = AggregationBuilders.filter("provinceid", QueryBuilders.queryStringQuery("440400"));
//         //4.按某个字段分组
//         TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_cityname").field("cityname");
//         //5.求和
//         SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum("sum_provinceid").field("provinceid");
//         //6.平均值
//         AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avg_provinceid").field("provinceid");
//         //7.最大值
//         MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("max_provinceid").field("provinceid");
//         //8.最小值
//         MinAggregationBuilder minAggregationBuilder = AggregationBuilders.min("min_provinceid").field("provinceid");
//         //9.日期间隔分组
//         DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("dh_provinceid").field("provinceid");
//         //10.获取聚合里面的结果
//         TopHitsAggregationBuilder topHitsAggregationBuilder = AggregationBuilders.topHits("top_provinceid");
//         //11.嵌套的聚合
//         NestedAggregationBuilder nestedAggregationBuilder = AggregationBuilders.nested("nested_path", "path");
//         //12.反转嵌套
//         ReverseNestedAggregationBuilder reverseNestedAggregationBuilder = AggregationBuilders.reverseNested("");
//         NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//         nativeSearchQueryBuilder.addAggregation(sumAggregationBuilder);
//         NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
// //        Aggregations aggregations = elasticsearchTemplate.query(nativeSearchQuery, new ResultsExtractor<Aggregations>() {
// //            @Override
// //            public Aggregations extract(SearchResponse searchResponse) {
// //                return searchResponse.getAggregations();
// //            }
// //        });
// //        Map<String, Aggregation> aggregationMap = aggregations.asMap();
// //        aggregationMap.forEach((k, v) -> {
// //            System.err.println(k);
// //            System.err.println(v);
// //            v.getSum()
// ////            System.out.println(v.getMetaData().get(k));
// //        });
// //        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
// //        Iterator<StringTerms.Bucket> iterator = buckets.iterator();
// //        while (iterator.hasNext()) {
// //            String provinceid = iterator.next().getKeyAsString();
// //            System.out.println(provinceid);
// //        }
// //        Page<City> cityPage = cityRepository.search(nativeSearchQuery);
// //        long total = cityPage.getTotalElements();
// //        System.out.println(total);
// //        List<City> cityList = cityPage.getContent();
// //        for (City city : cityList) {
// //            System.out.println(city);
// //        }
//         Page<City> cityPage = cityRepository.search(nativeSearchQuery);
//         for (City city : cityPage) {
//             System.out.println(city);
//         }
//     }
    @Override
    public void testMethod() {
//        this.a();
//        this.b();
//        this.c();
//        this.d();
//        this.e();
//         this.f();
    }
}