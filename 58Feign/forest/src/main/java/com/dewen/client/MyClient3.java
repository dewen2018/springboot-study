package com.dewen.client;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 三种写法是等价
 */
public interface MyClient3 {

//    @Request(
//            url = "${0}/send?un=${1}&pw=${2}&ph=${3}&ct=${4}",
//            type = "get",
//            dataType = "json"
//    )
//    public Map send(
//            String base,
//            String userName,
//            String password,
//            String phone,
//            String content
//    );

//    @Request(
//            url = "${base}/send?un=${un}&pw=${pw}&ph=${3}&ct=${ct}",
//            type = "get",
//            dataType = "json"
//    )
//    public Map send(
//            @DataVariable("base") String base,
//            @DataVariable("un") String userName,
//            @DataVariable("pw") String password,
//            @DataVariable("ph") String phone,
//            @DataVariable("ct") String content
//    );

    @Request(
            url = "${base}/send",
            type = "get",
            dataType = "json"
    )
    public Map send(
            @DataVariable("base") String base,
            @DataParam("un") String userName,
            @DataParam("pw") String password,
            @DataParam("ph") String phone,
            @DataParam("ct") String content
    );
}