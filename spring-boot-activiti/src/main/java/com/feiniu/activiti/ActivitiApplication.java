package com.feiniu.activiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.feiniu.activiti.dao.CompRepository;
import com.feiniu.activiti.dao.PersonRepository;
import com.feiniu.activiti.model.Comp;
import com.feiniu.activiti.model.Person;
import com.feiniu.activiti.service.ActivitiService;

@SpringBootApplication
public class ActivitiApplication {

	@Autowired
	private CompRepository compRepository;
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class, args);
	}

	// 初始化模拟数据
	@Bean
	public CommandLineRunner init(final ActivitiService myService) {
		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				if (personRepository.findAll().size() == 0) {
					personRepository.save(new Person("wtr"));
					personRepository.save(new Person("wyf"));
					personRepository.save(new Person("admin"));
				}
				if (compRepository.findAll().size() == 0) {
					Comp group = new Comp("feiniu");
					compRepository.save(group);
				}
			}
		};
	}
}
