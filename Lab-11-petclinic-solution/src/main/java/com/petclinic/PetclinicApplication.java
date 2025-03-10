package com.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetclinicApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PetclinicApplication.class);
		app.setAdditionalProfiles("dev");
		app.run(args);
	}

}
