package com.feiniu.mybatis.model;

import java.io.Serializable;

/**
 * 国家信息实体对象
 **/
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     **/
    private String countryname;

    /**
     * 代码
     **/
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