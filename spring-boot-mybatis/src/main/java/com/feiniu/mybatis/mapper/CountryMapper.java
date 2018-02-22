package com.feiniu.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.feiniu.framework.base.mapper.BaseMapper;
import com.feiniu.mybatis.model.Country;

/**
 * 根据CountryMapper.xml生成sql
 * 
 * @author chensheng
 */
@Mapper
public interface CountryMapper extends BaseMapper<Country> {

}