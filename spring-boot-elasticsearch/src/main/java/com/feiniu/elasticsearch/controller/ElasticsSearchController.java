package com.feiniu.elasticsearch.controller;

import java.util.UUID;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.elasticsearch.entity.Article;
import com.feiniu.elasticsearch.repository.ArticleRepository;
import com.feiniu.elasticsearch.util.ElasticsearchUtils;
import com.feiniu.elasticsearch.util.EsPage;

@RestController
@RequestMapping("/es")
public class ElasticsSearchController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ElasticsearchUtils utils;

	@RequestMapping("/add")
	public String add(@RequestParam String title, @RequestParam String url, @RequestParam String content) {
		Article article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setTitle(title);
		article.setUrl(url);
		article.setContent(content);
		articleRepository.save(article);
		return "success";
	}

	@RequestMapping("/list")
	public Iterable<Article> list() {
		return articleRepository.findAll();
	}

	@RequestMapping("/termQuery")
	public Iterable<Article> termQuery(@RequestParam String key) {
		QueryBuilder queryBuilder = QueryBuilders.termQuery("url", QueryParser.escape(key));
		return articleRepository.search(queryBuilder);
	}

	@RequestMapping("/matchQuery")
	public Iterable<Article> matchQuery(@RequestParam String key) {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("content", key);
		return articleRepository.search(queryBuilder);
	}

	@RequestMapping("/highlight")
	public EsPage highlight(@RequestParam String key) {
		EsPage page = utils.searchDataPage("fn", "article", 1, 20, "", "", false, "content", "content=" + key);
		return page;
	}

}
