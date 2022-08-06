package com.dewen.repository;

import com.dewen.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Long deleteById(String id);
}