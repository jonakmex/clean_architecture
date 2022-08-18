package com.jonak.condo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:condo-beans.xml")
public class CondoRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondoRestApiApplication.class, args);
	}

}
