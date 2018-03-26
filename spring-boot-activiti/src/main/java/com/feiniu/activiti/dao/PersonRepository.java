package com.feiniu.activiti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feiniu.activiti.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByPersonName(String personName);

}
