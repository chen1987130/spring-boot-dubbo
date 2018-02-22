package com.feiniu.data.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.feiniu.data.rest.model.Country;

@RepositoryRestResource(path="country")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
