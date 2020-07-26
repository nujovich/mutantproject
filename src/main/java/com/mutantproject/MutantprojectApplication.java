package com.mutantproject;

import com.mutantproject.repository.DnaRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories(basePackageClasses = DnaRepository.class)
@ComponentScan("com.mutantproject.*")
public class MutantprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantprojectApplication.class, args);
	}

}
