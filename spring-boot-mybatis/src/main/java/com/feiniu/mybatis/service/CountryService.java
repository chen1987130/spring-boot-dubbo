package com.feiniu.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feiniu.framework.base.mapper.BaseMapper;
import com.feiniu.framework.base.service.BaseService;
import com.feiniu.mybatis.mapper.CountryMapper;
import com.feiniu.mybatis.model.Country;

@Service
public class CountryService extends BaseService<Country, BaseMapper<Country>> {

	@Autowired
	public void setMapper(CountryMapper mapper) {
		this.mapper = mapper;
	}

	public void batchOpt() {
		{
			Country country = new Country();
			country.setCountryname("name_1");
			country.setCountrycode("code_1");
			mapper.insert(country);
		}

		if (1== 1) {
			throw new RuntimeException("操作失败");
		}

		{
			Country country = new Country();
			country.setCountryname("name_2");
			country.setCountrycode("code_2");
			mapper.insert(country);
		}
	}
}
