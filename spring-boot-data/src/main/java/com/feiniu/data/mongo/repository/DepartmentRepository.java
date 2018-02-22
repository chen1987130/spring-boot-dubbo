package com.feiniu.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.feiniu.data.mongo.model.Department;


public interface DepartmentRepository extends MongoRepository<Department, Long> {

}
