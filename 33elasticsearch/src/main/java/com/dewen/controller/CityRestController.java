package com.dewen.controller;

import com.dewen.entity.City;
import com.dewen.service.CityService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
@Api(tags = "城市index")
@ApiSort(2)
public class CityRestController {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Autowired
    private CityService cityService;

    /**
     * 保存
     *
     * @return
     * @RequestBody City city
     */
    @GetMapping(value = "/saveCity")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="form"),
    //         @ApiImplicitParam(name="password",value="密码",required=true,paramType="form"),
    //         @ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
    // })
    public void saveCity() {
        City city = new City();
        // city.setCityname("珠海");
        // city.setProvinceid(440400L);
        // city.setDescription("珠海，广东省地级市、省域副中心城市，是国务院批复确定的中国经济特区，珠江口西岸核心城市和滨海风景旅游城市、粤港澳大湾区重要节点城市。 [2]  [109]  位于广东省中南部，介于北纬21°48′-22°27′、东经113°03′-114°19′，东与香港、深圳隔海相望，南与澳门陆地相连， [8]  横琴新区与澳门隔江相望，西邻江门市，北与中山市接壤。面积为1736.45平方千米 [122]  。设有8个国家一类口岸， [119]  是珠三角中海洋面积最大、岛屿最多、海岸线最长的城市，海洋面积达6050平方千米。 [1]  [3]  [130]  根据第七次人口普查数据，截至2020年11月1日零时，珠海市常住人口为2439585人。 [121]  2021年，全市地区生产总值3881.75亿元。 [131] \n" +
        //         "珠海在历史上就属于广府地区，广府人是开创岭南文化的重要群体， [5]  珠海是广府文化的代表城市之一 [4]  ，是广府人走向海外的重要起航地之一，是中国最早设立的经济特区之一。 [5]  珠海1979年建市，1980年设立经济特区，享有全国人大赋予的地方立法权 [1]  。2020年，中国100座大中城市可持续发展综合排名中，珠海已经连续三年位列第一 [113]  。\n" +
        //         "珠海先后荣获“国家园林城市”、“中国优秀旅游城市”、“中国最具幸福感城市”、“国家森林城市”、“中国生态文明奖”等称号；联合国人居中心颁发的“国际改善居住环境最佳范例奖”、“中国最具有幸福感城市”。 [8]  2020年2月，被确定为第五批中央财政支持开展居家和社区养老服务改革试点地区。 [9]  2020年6月，经中央依法治国委入选为第一批全国法治政府建设示范地区和项目名单。 [10] ");
        city.setId("1");
        city.setCityname("广州");
        city.setProvinceid(440401L);
        city.setDescription("广东，简称“粤”，中华人民共和国省级行政区，省会广州。因古地名广信之东，故名“广东”。位于南岭以南，南海之滨，与香港、澳门、广西、湖南、江西及福建接壤，与海南隔海相望 [1]  。下辖21个地级市、65个市辖区、20个县级市、34个县、3个自治县。 [2]  截至2021年，广东省的常住人口为12684万人。 [74] \n" +
                "广东是岭南文化的重要传承地，在语言、风俗、生活习惯和历史文化等方面都有着独特风格。广东也是中国人口较多的省份之一。广东省属于东亚季风区，从北向南分别为中亚热带、南亚热带和热带气候，是全国光、热和水资源丰富的地区，是农耕文明发祥地之一。 [48]  [56] \n" +
                "广东是中国的南大门，处在南海航运枢纽位置上。早在三千多年前就已经形成以陶瓷为纽带的贸易交往圈，并通过水路将其影响扩大到沿海和海外岛屿。 [47]  到了清代，广州成为全国唯一的对外通商口岸。改革开放后，广东成为改革开放前沿阵地和引进西方经济、文化、科技的窗口。\n" +
                "自1989年起，广东国内生产总值连续居全国第一位 [5]  ，成为中国第一经济大省，经济总量占全国的1/8。广东省域经济综合竞争力居全国第一 [6]  。广东珠三角9市将联手港澳打造粤港澳大湾区，成为与纽约湾区、旧金山湾区、东京湾区并肩的世界四大湾区之一。 [7] \n" +
                "2021年广东地区生产总值为124369.67亿元，同比增长8.0%，两年平均增长5.1%。");
        cityService.saveCity(city);
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "通过城市名称查询", notes = "我是notes...", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public List<City> findByName(
            @ApiParam(name = "name", value = "城市名称", required = true) @RequestParam(value = "name") String name) {
        return cityService.findByName(name);
    }

    @GetMapping("/findByPage")
    public Page<City> findByPage() {
        //Sort.Direction.ASC,"createtime"
        Pageable page = PageRequest.of(0, 10);
        return cityService.findByPage(page);
    }

    @GetMapping("/findById/{id}")
    public City findById(@PathVariable(value = "id") String id) {
        return cityService.findById(id);
    }

    @GetMapping("/findByCitynameOrProvinceid")
    public List<City> findByCitynameOrProvinceid(@RequestParam(value = "cityname") String cityname, @RequestParam(value = "provinceid") Long provinceid) {
        return cityService.findByCitynameOrProvinceid(cityname, provinceid);
    }

    @GetMapping(value = "/deleteByCityname")
    public Long deleteByCityname(@RequestParam(value = "cityname") String cityName) {
        return cityService.deleteByCityname(cityName);
    }

    @GetMapping(value = "/deleteById")
    public Long deleteById(@RequestParam(value = "id") String id) {
        return cityService.deleteById(id);
    }

    // @GetMapping(value = "/city/search")
    // public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
    //                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
    //                              @RequestParam(value = "searchContent") String searchContent) {
    //     return cityService.searchCity(pageNumber, pageSize, searchContent);
    // }

    // @GetMapping(value = "/testMethod")
    // public void testMethod() {
    //     cityService.testMethod();
    // }

    @GetMapping("getHightLight")
    public List<City> getHightLight(String cityname) {
        //根据一个值查询多个字段  并高亮显示  这里的查询是取并集，即多个字段只需要有一个字段满足即可
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("cityname", cityname))
                .should(QueryBuilders.matchQuery("description", cityname));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("cityname")
                        , new HighlightBuilder.Field("description"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<City> search = elasticsearchRestTemplate.search(searchQuery, City.class);
        //得到查询返回的内容
        List<SearchHit<City>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<City> cityList = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<City> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setCityname(highlightFields.get("cityname") == null ? searchHit.getContent().getCityname() : highlightFields.get("cityname").get(0));
            searchHit.getContent().setDescription(highlightFields.get("description") == null ? searchHit.getContent().getDescription() : highlightFields.get("description").get(0));
            //放到实体类中
            cityList.add(searchHit.getContent());
        }
        return cityList;
    }
}