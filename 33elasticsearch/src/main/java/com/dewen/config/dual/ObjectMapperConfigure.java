package com.dewen.config.dual;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: For input string: "峨嵋派";
 * nested exception is com.fasterxml.jackson.databind.JsonMappingException: For input string: "峨嵋派" (through reference chain:
 * org.elasticsearch.action.search.SearchResponse["aggregations"]->org.elasticsearch.search.aggregations.Aggregations["asMap"]
 * ->java.util.Collections$UnmodifiableMap["sect_count"]->org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms["buckets"]->
 * java.util.ArrayList[0]->org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms$ParsedBucket["keyAsNumber"])]
 */
@Configuration
public class ObjectMapperConfigure {
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule());
        return objectMapper;
    }

    private SimpleModule simpleModule() {
        ParsedStringTermsBucketSerializer serializer = new ParsedStringTermsBucketSerializer(ParsedStringTerms.ParsedBucket.class);
        SimpleModule module = new SimpleModule();
        module.addSerializer(serializer);
        return module;
    }
}
