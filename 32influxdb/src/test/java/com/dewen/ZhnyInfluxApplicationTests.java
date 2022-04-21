//package com.dewen;
//
//import com.dewen.config.InfluxDBConnection;
//import org.influxdb.dto.QueryResult;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//class ZhnyInfluxApplicationTests {
//
//    @Test
//    void contextLoads() {
//        InfluxDBConnection influxDBConnection = new InfluxDBConnection("root", "rootroot", "http://127.0.0.1:8086", "testDB", "");
//
//        for (int i = 0; i < 1000; i++) {
//            HashMap<String, String> targs = new HashMap<>();
//            targs.put("host", "serverB");
//
//            HashMap<String, Object> fields = new HashMap<>();
//            fields.put("region", "us_wwww");
//            fields.put("time", System.currentTimeMillis());
//            fields.put("value", System.currentTimeMillis());
//            fields.put("f1", "0.8118");
//            fields.put("f2", "0.8118");
//
//            influxDBConnection.insert("cpu", targs, fields);
//        }
//
//
//        QueryResult results = influxDBConnection.query("SELECT count(1) FROM cpu");
//        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
//        QueryResult.Result oneResult = results.getResults().get(0);
//        if (oneResult.getSeries() != null) {
//            List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
//                    .collect(Collectors.toList()).get(0);
//            if (valueList != null && valueList.size() > 0) {
//                for (List<Object> value : valueList) {
//                    Map<String, String> map = new HashMap<String, String>();
//                    // 数据库中字段1取值
//                    String field1 = value.get(0) == null ? null : value.get(0).toString();
//                    // 数据库中字段2取值
//                    String field2 = value.get(1) == null ? null : value.get(1).toString();
//                    // TODO 用取出的字段做你自己的业务逻辑……
//                }
//            }
//        }
//
//    }
//
//}
