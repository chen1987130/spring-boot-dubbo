package com.feiniu.activiti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feiniu.activiti.model.Comp;

public interface CompRepository extends JpaRepository<Comp, Long> {
	
}
