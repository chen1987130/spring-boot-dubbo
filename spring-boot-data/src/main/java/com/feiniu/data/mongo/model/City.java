package com.feiniu.data.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.feiniu.data.mongo.annotation.AutoIncrease;

@Document(collection = "city")
public class City {

	@Id
	@AutoIncrease
	private Long id = 0L;

	@Field
	private String name;

	public City() {
	}

	public City(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
