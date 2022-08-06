package com.dewen.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public class EsConf {
    @Value("${elasticSearch.url}")
    private String elasticSearchUrl;
    // @Value("${elasticsearch.schema}")
    // private String schema;
    // @Value("${elasticsearch.address}")
    // private String address;
    // @Value("${elasticsearch.connectTimeout}")
    // private int connectTimeout;
    // @Value("${elasticsearch.socketTimeout}")
    // private int socketTimeout;
    // @Value("${elasticsearch.connectionRequestTimeout}")
    // private int tryConnTimeout;
    // @Value("${elasticsearch.maxConnectNum}")
    // private int maxConnNum;
    // @Value("${elasticsearch.maxConnectPerRoute}")
    // private int maxConnectPerRoute;

    //localhost:9200 写在配置文件中就可以了
    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticSearchUrl)
                .build();
        return RestClients.create(clientConfiguration).rest();
        // 拆分地址
        // List<HttpHost> hostLists = new ArrayList<>();
        // String[] hostList = address.split(",");
        // for (String addr : hostList) {
        //     String host = addr.split(":")[0];
        //     String port = addr.split(":")[1];
        //     hostLists.add(new HttpHost(host, Integer.parseInt(port), schema));
        // }
        // // 转换成 HttpHost 数组
        // HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
        // // 构建连接对象
        // RestClientBuilder builder = RestClient.builder(httpHost);
        // // 异步连接延时配置
        // builder.setRequestConfigCallback(requestConfigBuilder -> {
        //     requestConfigBuilder.setConnectTimeout(connectTimeout);
        //     requestConfigBuilder.setSocketTimeout(socketTimeout);
        //     requestConfigBuilder.setConnectionRequestTimeout(tryConnTimeout);
        //     return requestConfigBuilder;
        // });
        // // 异步连接数配置
        // builder.setHttpClientConfigCallback(httpClientBuilder -> {
        //     httpClientBuilder.setMaxConnTotal(maxConnNum);
        //     httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
        //     return httpClientBuilder;
        // });
        // return new RestHighLevelClient(builder);
    }
}