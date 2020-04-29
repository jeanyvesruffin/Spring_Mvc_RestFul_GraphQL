package com.ruffin.Spring_Mvc_Restful_GraphQl.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class PeopleHealthIndicator implements HealthIndicator {
	private final String message_key = "PeopleService";
	@Override
	public Health health() {
		if (!isRunningServicePeopleService()) {
			return Health.down().withDetail(message_key, "Not available").build();
		}
		return Health.up().withDetail(message_key, "Available").build();
	}
	private Boolean isRunningServicePeopleService() {
		Boolean isRunning = false;
		// Add real logic here to test if People Service is running; 
		// skipped for demo purposes
		// cad indiquer l'indicateur par exemple que vous voulez retourner
		return isRunning;
	}
}
