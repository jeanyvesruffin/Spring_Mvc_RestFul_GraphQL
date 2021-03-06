package com.ruffin.Spring_Mvc_Restful_GraphQl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringMvcRestfulGraphQlApplication {

	// Utilisation d'un sf4j
	// private static final Logger log = LoggerFactory.getLogger(SpringMvcRestfulGraphQlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcRestfulGraphQlApplication.class, args);
	}

	
	// Test populate H2 = perrsistant data in database H2
	// Simple query to populate DB
//	@Bean
//	public CommandLineRunner demo(IApplicationRepository repository) {
//		return (args) -> {
//			repository.save(new Application("TrackZilla", "Jean-Yves.Ruffin", "Application for tracking bugs."));
//			repository.save(new Application("Expenses", "Mary.Jones","Application to track expense reports."));
//			repository.save(new Application("Notification", "Karen.Kane", "Application to send alerts and notifications."));
//
//			for(Application application: repository.findAll()) {
//				log.info("The application is: "+ application.toString());
//			}
//		};
//	}
}
