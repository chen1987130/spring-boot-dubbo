package com.feiniu.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.feiniu.elasticsearch.entity.Article;

@Component
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

}
