package com.feiniu.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.feiniu.data.mongo.model.City;

public interface CityRepository extends MongoRepository<City, Long> {

}
