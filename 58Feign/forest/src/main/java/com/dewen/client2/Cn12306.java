package com.dewen.client2;

import com.dtflys.forest.annotation.Request;

public interface Cn12306 {

    @Request(url = "${idServiceUrl}")
    String index();
}