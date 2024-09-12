package com.Housing.Housing;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class HousingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousingApplication.class, args);
	}

}
