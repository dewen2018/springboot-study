package com.dewen.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "article")
@ApiModel(value = "article", description = "文章")
public class Article {
    @Id
    // @JsonIgnore
    private Long id;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field
    private String createDate;
    @Field
    private String url;
}