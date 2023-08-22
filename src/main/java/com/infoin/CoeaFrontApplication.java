package com.infoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoeaFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoeaFrontApplication.class, args);
	}

}
