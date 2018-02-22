package com.feiniu.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.feiniu.data.mongo.model.User;


public interface UserRepository extends MongoRepository<User, Long> {

	User findByUsername(String username);

}
