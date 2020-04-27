package com.ruffin.Spring_Mvc_Restful_GraphQl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ruffin.Spring_Mvc_Restful_GraphQl.entity.Application;
import com.ruffin.Spring_Mvc_Restful_GraphQl.repository.IApplicationRepository;



@SpringBootApplication
public class SpringMvcRestfulGraphQlApplication {

	// Utilisation d'un sf4j
	private static final Logger log = LoggerFactory.getLogger(SpringMvcRestfulGraphQlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcRestfulGraphQlApplication.class, args);
	}

	// Simple query to populate DB
	@Bean
	public CommandLineRunner demo(IApplicationRepository repository) {
		return (args) -> {
			repository.save(new Application("TrackZilla", "Jean-Yves.Ruffin", "Application for tracking bugs."));
			repository.save(new Application("Expenses", "Mary.Jones","Application to track expense reports."));
			repository.save(new Application("Notification", "Karen.Kane", "Application to send alerts and notifications."));

			for(Application application: repository.findAll()) {
				log.info("The application is: "+ application.toString());
			}
		};
	}
}
