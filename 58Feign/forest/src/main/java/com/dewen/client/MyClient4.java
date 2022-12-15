package com.dewen.client;

import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;

import java.util.Map;

public interface MyClient4 {

    /**
     * 把参数绑定到header和body里去，你甚至于可以用一些表达式简单的把对象序列化成json或者xml
     * @param request
     * @param auth
     * @return
     */
//    @Request(
//            url = "${base}/pay",
//            contentType = "application/json",
//            type = "post",
//            dataType = "json",
//            headers = {"Authorization: ${1}"},
//            data = "${json($0)}"
//    )
//    public PayResponse pay(PayRequest request, String auth);

    /**
     * https支持
     */
//    @Request(
//            url = "${base}/pay",
//            contentType = "application/json",
//            type = "post",
//            dataType = "json",
//            data = "${json($0)}",
//            keyStore = "pay-keystore"
//    )
//    public PayResponse pay(PayRequest request);
}