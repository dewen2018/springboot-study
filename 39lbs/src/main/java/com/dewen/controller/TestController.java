package com.dewen.controller;

import com.dewen.entity.Author;
import com.dewen.entity.Book;
import com.dewen.repository.BookRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/test")
public class TestController {
    final String BOOK_COLLECTION = "dewen_book";
    @Resource
    BookRepository bookRepository;

    @Resource
    MongoTemplate mongoTemplate;

    @GetMapping("/testSaveOne")
    public void testSaveOne() {
        Book book = new Book("高等数学上",
                37.70,
                "高等教育出版社"
                , new Author[]{new Author("同济大学数学系", "")},
                new String[]{"数学", "大学数学", "高等数学"});

        final Book ret = bookRepository.insert(book);
        // final Book ret = mongoTemplate.insert(book, BOOK_COLLECTION);
        System.out.println(ret);
    }

    @GetMapping("/testSaveBatch")
    public void testSaveBatch() {
        List<Book> bookList = Arrays.asList(
                new Book("TCP/IP详解卷1：协议", 45.00, "机械工业出版社", new Author[]{
                        new Author("W.Richard Stevens", "男"),
                        new Author("范建华", "男"),
                        new Author("胥光辉", "男"),
                        new Author("张涛", "男"),
                        new Author("谢希仁", "男")
                }, new String[]{"计算机网络", "网络协议", "编程"}),

                new Book("Python程序设计开发宝典", 69.00, "清华大学出版社", new Author[]{new Author(
                        "董付国", "男")}, new String[]{"程序设计", "编程", "python"}),

                new Book("汇编语言", 36.00, "清华大学出版社", new Author[]{new Author("王爽", "")},
                        new String[]{"程序设计", "编程", "汇编"}),

                new Book("SparkSQL入门与实践指南", 49.00, "清华大学出版社", new Author[]{
                        new Author("纪涵", "女"),
                        new Author("靖晓文", "女"),
                        new Author("赵政达", "男")
                }, new String[]{"程序设计", "编程", "spark", "spark sql"})
        );

        final Collection<Book> ret = bookRepository.insert(bookList);
        // final Collection<Book> ret = bookRepository.saveAll(bookList);
        // final Collection<Book> ret = mongoTemplate.insert(bookList, BOOK_COLLECTION);
        System.out.println(ret.size());
    }

    @GetMapping("/testEqual1")
    public void testEqual1() {
        // 使用静态方法创建 Criteria
        Criteria criteria = Criteria.where("name").is("Python程序设计开发宝典");

        // new Criteria 实例,然后设置变量参数
        //  Criteria criteria = new Criteria();
        //  criteria.and("name").is("Python程序设计开发宝典");

        Query query = new Query(criteria);
        // 如果只想查询一条或者明确知道仅有一条, 使用 findOne,否则,可以使用 find 或 findAll
        // final Book ret = mongoTemplate.findOne(query, Book.class, BOOK_COLLECTION);
        final Book ret = mongoTemplate.findOne(query, Book.class, BOOK_COLLECTION);
        assert ret != null;

        // Book book = new Book();
        // book.setName("Python程序设计开发宝典");
        // ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("xx");
        // Example<Book> example = Example.of(book);
        // bookRepository.findOne(example);
    }

    @GetMapping("/testEqual2")
    public void testEqual2() {
        // 使用静态方法创建 Criteria
        Criteria criteria = Criteria.where("press").is("清华大学出版社");

        Query query = new Query(criteria);
        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 3;
    }


    public void testGt() {
        Criteria criteria = Criteria.where("price").gt(30);
        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 5;
    }

    public void testGte() {
        Criteria criteria = Criteria.where("price").gte(45);
        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 3;
    }

    public void testLt() {
        Criteria criteria = Criteria.where("price").lt(45);
        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 2;
    }

    public void testLte() {
        Criteria criteria = Criteria.where("price").lte(45);
        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 3;
    }

    public void testNe() {
        Criteria criteria = Criteria.where("price").ne(45);
        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 4;
    }

    /**
     * 多条件查询
     */

    public void testAnd() {
        Criteria criteria = new Criteria();
        criteria.and("price").gt(45)
                .and("press").is("清华大学出版社");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 2;
    }

    public void testOr() {
        Criteria criteria = new Criteria();

        // 每个条件构建一个 criteria 对象
        Criteria criteria1 = Criteria.where("price").lt(45);
        Criteria criteria2 = Criteria.where("press").is("清华大学出版社");

        // 将多个 criteria 对象使用  or 操作关联起来
        criteria.orOperator(criteria1, criteria2);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 4;
    }
    // 相当于 not(price < 45 or press='清华大学出版社')

    public void testNot() {
        Criteria criteria = new Criteria();

        Criteria criteria1 = Criteria.where("price").lt(45);
        Criteria criteria2 = Criteria.where("press").is("清华大学出版社");

        criteria.norOperator(criteria1, criteria2);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
    }

    public void testIn() {
        Criteria criteria = Criteria.where("press").in("机械工业出版社", "清华大学出版社");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
    }

    public void testNin() {
        Criteria criteria = Criteria.where("press").nin("机械工业出版社", "清华大学出版社");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }

    /**
     * 正则查询
     */

    public void testBeginWith() {
        // 定义正则表达式
        String name = "高等";
        Pattern pattern = Pattern.compile("^" + name + ".*$", Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("name").regex(pattern);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }

    public void testContain() {
        String name = "开发";
        Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("name").regex(pattern);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }

    /**
     * 结尾包含字符串
     */
    public void testEndWith() {
        String name = "指南";
        Pattern pattern = Pattern.compile("^.*" + name + "$", Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("name").regex(pattern);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }

    /**
     * 数组查询
     */
    // 数组中包含查询单值
    public void testArrayMatchSingle() {
        Criteria criteria = Criteria.where("tags").is("编程");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 4;
    }
    // 数组中同时包含指定的多个值，不要求顺序

    public void testArrayMatchAll() {
        Criteria criteria = Criteria.where("tags").all("编程", "程序设计");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 3;
    }

    public void testArrayMatchSize() {
        Criteria criteria = Criteria.where("tags").size(4);

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }
    // 满足特定索引下条件
// 数组索引从 0 开始，匹配第二项就用 tags.1 作为键

    public void testMatchIndex() {
        Criteria criteria = Criteria.where("tags.1").is("编程");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 3;
    }
    // 元素个数和顺序都要匹配

    public void testArrayMatch() {
        Criteria criteria = Criteria.where("tags").is(new String[]{"程序设计", "编程", "python"});

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 1;
    }

    public void testMatchSubDoc() {
        Criteria criteria = Criteria.where("authors.name").is("纪涵");

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);

        assert books.size() == 1;
    }

    public void testElementMatch() {
        Criteria criteria = Criteria.where("authors").elemMatch(Criteria.where("name").is("谢希仁").and("sex").is("男"));

        Query query = new Query(criteria);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);

        assert books.size() == 1;
    }

    public void testCount() {
        Query query = new Query();
        final long ret = mongoTemplate.count(query, BOOK_COLLECTION);
        System.out.println(ret);
    }

    public void testDistinct() {
        final List<String> distinctPress = mongoTemplate.findDistinct(new Query(), "press", BOOK_COLLECTION, String.class);
        System.out.println(distinctPress);
    }

    public void testSum() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id", "press", "price"),
                Aggregation.group("press")
                        .count().as("count")
                        .sum("price").as("total")
                        .avg("price").as("avg")
        );

        final AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, BOOK_COLLECTION, Map.class);
        final List<Map> mappedResults = aggregationResults.getMappedResults();
        System.out.println(mappedResults);
    }

    public void testSum2() {
        // 定义乘法表达式, 可以是两个字段相乘或者字段与数字相乘,同理，ArithmeticOperators 包含 加减乘除模等操作
        final ArithmeticOperators.Multiply price = ArithmeticOperators.valueOf("PRICE").multiplyBy(5);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id", "press", "PRICE"),
                Aggregation.group("press")
                        .count().as("count")
                        .sum(price).as("total")
                        .avg(price).as("avg")
        );


        final AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, BOOK_COLLECTION, Map.class);
        final List<Map> mappedResults = aggregationResults.getMappedResults();
        System.out.println(mappedResults);
    }

    public void testSortAndLimit() {
        Criteria criteria = Criteria.where("press").is("清华大学出版社");
        Query query = new Query(criteria);

        // with 方法设置排序
        query.with(Sort.by(Sort.Direction.ASC, "price"));
        // skip limit 方法设置分页
        query.skip(1).limit(10);

        final List<Book> books = mongoTemplate.find(query, Book.class, BOOK_COLLECTION);
        assert books.size() == 2;
    }

    public void testUpdateField() {
        Query query = new Query(Criteria.where("_id").is("614303980f84fe24d7618f28"));

        Update update = new Update();
        update.set("name", "高等数学");

        final UpdateResult result = mongoTemplate.updateFirst(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testUpdateNumber() {
        Query query = new Query(Criteria.where("_id").is("614303980f84fe24d7618f28"));

        Update update = new Update();
        // inc 方法指定数字增加多少
        update.inc("price", 30.04);

        final UpdateResult result = mongoTemplate.updateFirst(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testMultiply() {
        Query query = new Query(Criteria.where("_id").is("614303980f84fe24d7618f28"));
        Update update = new Update().multiply("price", 0.3);

        final UpdateResult result = mongoTemplate.updateFirst(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testRenameFiled() {
        Query query = new Query();
        Update update = new Update().rename("price", "PRICE");

        final UpdateResult result = mongoTemplate.updateMulti(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testAddField() {
        Query query = new Query();
        Update update = new Update().set("workspaceId", "default");

        final UpdateResult result = mongoTemplate.updateMulti(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testRemoveField() {
        Query query = new Query();
        Update update = new Update().unset("_class");

        final UpdateResult result = mongoTemplate.updateMulti(query, update, BOOK_COLLECTION);
        assert result.wasAcknowledged();
    }

    public void testAddToArray() {
        Query query = new Query(Criteria.where("_id").is("614303980f84fe24d7618f28"));

        Update update = new Update();
        // 添加一个,数组中存在元素在忽略添加
        //update.addToSet("tags").value("a");
        // 添加多个,数组中存在元素则忽略添加
        //update.addToSet("tags").each("a", "b", "c", "c");

        // 无论是否存在,都添加到数组, 可以通过 atPosition 指定存放位置
        update.push("tags").atPosition(1).value("101");

        final UpdateResult ret = mongoTemplate.updateFirst(query, update, BOOK_COLLECTION);
        assert ret.wasAcknowledged();
    }

    public void testRemoveArray() {
        Query query = new Query(Criteria.where("_id").is("614303980f84fe24d7618f28"));

        Update update = new Update();
        // 移除单个数据
        //update.pull("tags","a");
        // 移除多个数据
        update.pullAll("tags", new Object[]{"a", "b", "101"});

        final UpdateResult ret = mongoTemplate.updateFirst(query, update, BOOK_COLLECTION);
        assert ret.wasAcknowledged();
    }
    // 数组内字符串更新

    public void testUpdateArray1() {
        Query query = new Query();

        // 将标签数组中，所有 "编程" 替换为 "程序设计"
        Update update = new Update();
        update.set("tags.$[element]", "程序设计");
        update.filterArray(Criteria.where("element").is("编程"));

        final UpdateResult ret = mongoTemplate.updateMulti(query, update, BOOK_COLLECTION);
        assert ret.wasAcknowledged();
    }

    // 数组内子文档更新

    public void testUpdateArray2() {
        Query query = new Query();
        Update update = new Update();
        // 将作者姓名为 "王爽" 替换为 "王爽爽"
        update.set("authors.$[element].name", "王爽爽");
        update.filterArray(Criteria.where("element.name").is("王爽"));

        // AggregationUpdate update = Aggregation.newUpdate()
        //         .set("average").toValue(ArithmeticOperators.valueOf("tests").avg())
        //         .set("grade").toValue(ConditionalOperators.switchCases(
        //                 when(valueOf("average").greaterThanEqualToValue(90)).then("A"),
        //                 when(valueOf("average").greaterThanEqualToValue(80)).then("B"),
        //                 when(valueOf("average").greaterThanEqualToValue(70)).then("C"),
        //                 when(valueOf("average").greaterThanEqualToValue(60)).then("D"))
        //                 .defaultTo("F")
        //         );

        final UpdateResult ret = mongoTemplate.updateMulti(query, update, BOOK_COLLECTION);
        assert ret.wasAcknowledged();


    }

    public void testDelete() {
        Criteria criteria = Criteria.where("authors.name").is("王爽爽");
        Query query = new Query(criteria);
        final DeleteResult ret = mongoTemplate.remove(query, BOOK_COLLECTION);

        assert ret.wasAcknowledged();
    }

    /**
     * 查询案例
     */
    public void test01() {
        // // 创建条件对象
        // Criteria criteria = new Criteria();
        // // 3. 单个条件查询多个字段 (客户编号)
        // if (StringUtils.isNotEmpty(bo.getAdmpId())) {
        //     criteria.orOperator(
        //             Criteria.where("final_uid").is(bo.getAdmpId()),
        //             Criteria.where("customer_ids").in(bo.getAdmpId()),
        //             Criteria.where("official_ids").in(bo.getAdmpId()),
        //             Criteria.where("tb_ids").in(bo.getAdmpId()),
        //             Criteria.where("jd_ids").in(bo.getAdmpId()),
        //             Criteria.where("yz_ids").in(bo.getAdmpId()),
        //             Criteria.where("wm_ids").in(bo.getAdmpId()),
        //             Criteria.where("dd_ids").in(bo.getAdmpId()),
        //             Criteria.where("ks_ids").in(bo.getAdmpId())
        //     );
        // }
        // // 2. 模糊查询 (客户名称模糊搜索)
        // if (StringUtils.isNotBlank(bo.getName())) {
        //     criteria.and("name").regex(Pattern.compile("^.*" + bo.getName() + ".*$", Pattern.CASE_INSENSITIVE));
        // }
        // // 1. 全等于 (手机号全字匹配)
        // if (StringUtils.isNotBlank(bo.getMobile())) {
        //     criteria.and("mobile").is(bo.getMobile());
        // }
        // if (StringUtils.isNotBlank(bo.getBindStatus())) {
        //     criteria.and("bind_status").is(bo.getBindStatus());
        // }
        // if (StringUtils.isNotBlank(bo.getRetentionStatus())) {
        //     criteria.and("retention_status").is(bo.getRetentionStatus());
        // }
        // // 4. 日期范围 (近期消费时间)
        // if (StringUtils.isNotEmpty(bo.getRecentlyBuyBeginTime()) && StringUtils.isNotEmpty(bo.getRecentlyBuyEndTime())) {
        //     criteria.andOperator(Criteria.where("recently_buy_time").gte(bo.getRecentlyBuyBeginTime()), Criteria.where("recently_buy_time").lte(bo.getRecentlyBuyEndTime()));
        // }
        // if (StringUtils.isNotNull(bo.getLowestTotalBuyAmount()) && StringUtils.isNotNull(bo.getHighestTotalBuyAmount())) {
        //     criteria.and("total_buy_amount").gte(bo.getLowestTotalBuyAmount()).lte(bo.getHighestTotalBuyAmount());
        // }
        // if (StringUtils.isNotNull(bo.getLowestTotalBuyAmount()) && StringUtils.isNull(bo.getHighestTotalBuyAmount())) {
        //     criteria.and("total_buy_amount").gte(bo.getLowestTotalBuyAmount());
        // }
        // if (StringUtils.isNull(bo.getLowestTotalBuyAmount()) && StringUtils.isNotNull(bo.getHighestTotalBuyAmount())) {
        //     criteria.and("total_buy_amount").lte(bo.getHighestTotalBuyAmount());
        // }
        // // 5. 数值范围 (消费总金额)
        // if (StringUtils.isNotNull(bo.getMinTotalBuyTimes()) && StringUtils.isNotNull(bo.getMaxTotalBuyTimes())) {
        //     criteria.and("total_buy_count").gte(bo.getMinTotalBuyTimes()).lte(bo.getMaxTotalBuyTimes());
        // }
        // if (StringUtils.isNotNull(bo.getMinTotalBuyTimes()) && StringUtils.isNull(bo.getMaxTotalBuyTimes())) {
        //     criteria.and("total_buy_count").gte(bo.getMinTotalBuyTimes());
        // }
        // if (StringUtils.isNull(bo.getMinTotalBuyTimes()) && StringUtils.isNotNull(bo.getMaxTotalBuyTimes())) {
        //     criteria.and("total_buy_count").lte(bo.getMaxTotalBuyTimes());
        // }
        // if (!CollectionUtils.isEmpty(Arrays.asList(bo.getAdmpLabels()))) {
        //     if ("all".equals(bo.getTagScope())) {
        //         //  7. 数组字段满足全部 (客户标签)
        //         criteria.and("admp_labels").all(bo.getAdmpLabels());
        //     } else if ("any".equals(bo.getTagScope())) {
        //         criteria.and("admp_labels").in(bo.getAdmpLabels());
        //     }
        // }
        // if (StringUtils.isNotEmpty(bo.getReceiverMobile())) {
        //     criteria.and("receiver_mobiles").in(bo.getReceiverMobile());
        // }
        // // 6. 数组字段满足任一 (来源平台、下单店铺)
        // if (StringUtils.isNotNull(bo.getPlatformTypes()) && bo.getPlatformTypes().length > 0) {
        //     criteria.and("source_codes").in(bo.getPlatformTypes());
        // }
        // if (StringUtils.isNotNull(bo.getShopIds()) && bo.getShopIds().length > 0) {
        //     criteria.and("shop_ids").in(bo.getShopIds());
        // }
        // if (StringUtils.isNotNull(bo.getReceiverProvince()) && bo.getReceiverProvince().length > 0) {
        //     criteria.and("receiver_provinces").in(bo.getReceiverProvince());
        // }
        // if (StringUtils.isNotNull(bo.getReceiverCity()) && bo.getReceiverCity().length > 0) {
        //     criteria.and("receiver_cities").in(bo.getReceiverCity());
        // }
        // if (StringUtils.isNotNull(bo.getReceiverDistrict()) && bo.getReceiverDistrict().length > 0) {
        //     criteria.and("receiver_districts").in(bo.getReceiverDistrict());
        // }
        // Query query = new Query();
        // query.addCriteria(criteria);
        // // 12. 总记录数
        // long total = mongoTemplate.count(query, ClientBasicInfoDO.class);
        // // 8. 查询返回指定字段 (自定义列表)
        // query.fields().include("final_uid", "name", "wechat_id", "mobile", "u_id", "retention_status", "tb_ids", "jd_ids", "yz_ids", "tb_ids", "wm_ids", "dd_ids", "ks_ids");
        // // 10. 分页
        // query.with(PageRequest.of(bo.getPageNum() - 1, bo.getPageSize(),
        //         // 11. 排序
        //         Sort.by(Sort.Order.desc("earliest_add_time"))));
        // // 执行查询
        // List<ClientBasicInfoDO> list = mongoTemplate.find(query, ClientBasicInfoDO.class);

    }


}
