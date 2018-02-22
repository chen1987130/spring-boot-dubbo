package com.feiniu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.feiniu.framework.paginter.domain.PageBounds;
import com.feiniu.framework.paginter.domain.PageList;
import com.feiniu.mybatis.model.City;
import com.feiniu.mybatis.model.Country;

/**
 * 根据注解生成sql
 * 
 * @author chensheng
 *
 */
@Mapper
public interface CityMapper {

	@Select("SELECT * FROM CITY")
	List<City> finaAll();

	@Select("SELECT * FROM CITY where id = #{id}")
	City selectOne(Integer id);

	@Select("SELECT * FROM COUNTRY where name = #{name}")
	PageList<Country> queryForPageByMap(Map<String, ?> city, PageBounds pageBounds);

	@Select("SELECT * FROM COUNTRY")
	PageList<Country> queryForPage(PageBounds pageBounds);

}
