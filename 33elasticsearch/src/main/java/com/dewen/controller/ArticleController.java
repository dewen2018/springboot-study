package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dewen.entity.Article;
import com.dewen.repository.ArticleRepository;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Api(tags = "article index")
@ApiSupport(order = 4)
// @ApiSort(4)
public class ArticleController {
    private static final String ARTICLE_INDEX = "article";

    @Autowired
    private RestHighLevelClient client;

    @Resource
    private ArticleRepository articleRepository;

    @ApiOperation(value = "删除index")
    @ApiOperationSupport(order = 0)
    @GetMapping("/deleteIndexOfArticle")
    public boolean deleteIndexOfArticle() {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest(ARTICLE_INDEX);

        AcknowledgedResponse response = null;
        try {
            response = client.indices().delete(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            System.err.println(response.isAcknowledged() ? "success" : "default");
            return response.isAcknowledged();
        } else {
            return false;
        }
    }

    @ApiOperation(value = "创建index")
    @ApiOperationSupport(order = 1)
    @GetMapping("/createIndexOfArticle")
    public boolean createIndexOfArticle() {
        Settings settings = Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
                .build();

        String mapping = "{\n" +
                "    \"properties\": {\n" +
                "        \"author\": {\n" +
                "            \"type\": \"text\"\n" +
                "        },\n" +
                "        \"content\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "        },\n" +
                "        \"title\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "        },\n" +
                "        \"createDate\": {\n" +
                "            \"type\": \"date\",\n" +
                "            \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\n" +
                "        },\n" +
                "        \"url\": {\n" +
                "            \"type\": \"text\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        CreateIndexRequest indexRequest = new CreateIndexRequest(ARTICLE_INDEX)
                .settings(settings)
                .mapping(mapping, XContentType.JSON);
        CreateIndexResponse response = null;

        try {
            response = client.indices()
                    .create(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            System.err.println(response.isAcknowledged() ? "success" : "default");
            return response.isAcknowledged();
        } else {
            return false;
        }
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(httpMethod = "POST", value = "添加文档addArticle", notes = "添加文档", response = IndexResponse.class)
    // @ApiResponses({
    //         @ApiResponse(code = 200, message = "非HTTP状态码，返回值JSON code字段值，描述：成功")
    // })
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "id",paramType ="form",value = "参数",allowMultiple = true, required = true)
    // })
    @PostMapping("/addArticle")
    public IndexResponse addArticle(@RequestBody Article article) {
        String articleString = JSONObject.toJSONString(article);
        //创建索引创建对象
        IndexRequest indexRequest = new IndexRequest(ARTICLE_INDEX);
        //文档内容
        indexRequest.source(articleString, XContentType.JSON)
                .id(String.valueOf(article.getId()));
        //通过client进行http的请求
        IndexResponse re = null;
        try {
            re = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }

    @PostMapping("/addArticle2")
    public Article addArticle2(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除doc")
    @GetMapping("/deleteArticle")
    public Long deleteArticle(String docId) {
        return articleRepository.deleteById(docId);
    }

    @ApiOperationSupport(order = 4)
    @GetMapping("/transferFromMysql")
    public String transferFromMysql() {
        articleRepository.findAll().forEach(this::addArticle);
        return "successful";
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "查询doc")
    @GetMapping("/queryByKey")
    public List<Article> queryByKey(String keyword) {
        SearchRequest request = new SearchRequest();
        /*
         * 创建  搜索内容参数设置对象:SearchSourceBuilder
         * 相对于matchQuery，multiMatchQuery针对的是多个fi eld，也就是说，当multiMatchQuery中，fieldNames参数只有一个时，其作用与matchQuery相当；
         * 而当fieldNames有多个参数时，如field1和field2，那查询的结果中，要么field1中包含text，要么field2中包含text。
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(
                QueryBuilders.multiMatchQuery(keyword, "author", "content", "title")
        );
        request.source(searchSourceBuilder);
        List<Article> result = new ArrayList<>();
        try {
            SearchResponse search = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                Map<String, Object> map = hit.getSourceAsMap();
                Article item = new Article();
                item.setAuthor((String) map.get("author"));
                item.setContent((String) map.get("content"));
                item.setTitle((String) map.get("title"));
                item.setUrl((String) map.get("url"));
                result.add(item);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperationSupport(order = 6)
    @GetMapping("/queryById")
    public Article queryById(String docId) {
        GetRequest request = new GetRequest(ARTICLE_INDEX, docId);
        GetResponse response = null;
        try {
            response = client.get(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.isExists()) {
            return JSONObject.parseObject(response.getSourceAsString(), Article.class);
        }
        return null;
    }


    @ApiOperationSupport(order = 7)
    @PostMapping("/edit")
    //es单条操作--修改文档（记录）　　
    public UpdateResponse edit(@RequestBody Article article) throws IOException {
        //创建修改文档的请求对象
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(ARTICLE_INDEX);
        updateRequest.id(String.valueOf(article.getId()));

        // updateRequest.doc(XContentFactory.jsonBuilder().startObject()
        //         // 对没有的字段添加, 对已有的字段替换
        //         .field("author", article.getAuthor())
        //         .field("title", article.getTitle())
        //         .field("content", article.getContent())
        //         .field("createDate", article.getCreateDate())
        //         .endObject());
        //以json格式添加文档信息
        updateRequest.doc(JSON.toJSONString(article), XContentType.JSON);

        //像es服务器发送请求
        UpdateResponse update = null;
        try {
            update = this.client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return update;
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "更新byQuery")
    @GetMapping("/customUpdateCondition")
    public void customUpdateCondition() throws IOException {
        //组装各种更新数据的条件。相当于mysql中update语句内的where部分  must=and   should=or
        QueryBuilder eduQueryBuilder = QueryBuilders.termQuery("education.keyword", "本科");
        QueryBuilder weightQueryBuilder = QueryBuilders.termQuery("weight", 110);
        RangeQueryBuilder totalScoreRangeQueryBuilder = QueryBuilders.rangeQuery("totalScore").gte(86).lte(1000);

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(eduQueryBuilder)
                .should(weightQueryBuilder)
                .should(totalScoreRangeQueryBuilder);

        //指定具体要操作的索引
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(ARTICLE_INDEX);

        updateByQueryRequest.setQuery(queryBuilder);
        //加载更新数据。相当于mysql中update语句内的set部分
        String setValuesStr = "ctx._source.userName='小明1';ctx._source.height='5544'";
        Script script = new Script(ScriptType.INLINE, "painless", setValuesStr, Collections.emptyMap());
        updateByQueryRequest.setScript(script);

        //更新
        this.client.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
    }
}
