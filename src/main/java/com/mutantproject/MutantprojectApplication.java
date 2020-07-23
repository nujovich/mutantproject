package com.mutantproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MutantprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantprojectApplication.class, args);
	}

}
