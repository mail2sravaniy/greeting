package com.centime.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service 2 - Greeting Application
 * 
 * Simple greeting service that provides "Hello" message when called by Service
 * 1.
 * 
 * As specified in requirements: "It contains one get method which is called by
 * the first service to fetch a string 'Hello' wrapped with a spring response
 * entity"
 */
@SpringBootApplication
public class GreetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);

		System.out.println("");
		System.out.println("👋 Service 2 (Greeting) started successfully!");
		System.out.println("🌐 Server: http://localhost:8082");
		System.out.println("🏥 Health Check: http://localhost:8082/api/health");
		System.out.println("👋 Greeting API: http://localhost:8082/api/greeting");
		System.out.println("🔗 Integration: Called by Service 1 for orchestration");
		System.out.println("");
	}
}
