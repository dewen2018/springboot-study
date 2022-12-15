package com.dewen.retrefitinterface;

import com.dewen.annotation.Sign;
import com.dewen.entity.Person;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@RetrofitClient(baseUrl = "${test.baseUrl}", poolName = "test1")
//@Intercept(handler = TimeStampInterceptor.class, include = {"/api/**"}, exclude = "/api/test/savePerson")
@Sign(accessKeyId = "${test.accessKeyId}", accessKeySecret = "${test.accessKeySecret}", exclude = {"/api/test/person"})
public interface HttpApi {

    @GET("/api/getPersonById")
    Person getPersonById(@Query("id") Integer id);

    @POST("/api/savePerson")
    Person savePerson(@Body Person person);
}