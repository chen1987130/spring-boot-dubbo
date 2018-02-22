package com.feiniu.data.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 国家信息实体对象
 **/

@Entity
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	/**
	 * 名称
	 **/
	@Column
	private String countryname;

	/**
	 * 代码
	 **/
	@Column
	private String countrycode;

	public Country() {
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountryname() {
		return this.countryname;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

}