package com.feiniu.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "fn", type = "article")
public class Article {

	@Id
	private String id;

	@Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
	private String title;

	@Field(type = FieldType.Text)
	private String url;

	@Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
