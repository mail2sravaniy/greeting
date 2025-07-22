package com.centime.greeting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Service 2 - Greeting Service
 * 
 * As specified in requirements: "It contains one get method which is called by
 * the first service to fetch a string 'Hello' wrapped with a spring response
 * entity"
 */
@RestController
@RequestMapping("/api")
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	/**
	 * Health check endpoint
	 */
	@GetMapping("/health")
	public ResponseEntity<String> health() {
		logger.info("Service 2 health check called");
		return ResponseEntity.ok("Up");
	}

	/**
	 * Main greeting endpoint
	 * 
	 * As specified in requirements: "one get method which is called by the first
	 * service to fetch a string 'Hello' wrapped with a spring response entity"
	 * 
	 * This method: 1. Is called by Service 1 as part of orchestration 2. Returns
	 * "Hello" wrapped in ResponseEntity 3. Supports trace ID propagation for
	 * logging
	 */
	@GetMapping("/greeting")
	public ResponseEntity<String> getGreeting(@RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

		if (traceId != null) {
			MDC.put("traceId", traceId);
		}

		logger.info("[TraceID: {}] GET /api/greeting - Greeting request received", traceId);

		try {
			// Return "Hello" wrapped with Spring ResponseEntity as specified
			String greeting = "Hello";

			logger.info("[TraceID: {}] GET /api/greeting - Returning greeting: {}", traceId, greeting);

			return ResponseEntity.ok(greeting);

		} finally {
			MDC.clear();
		}
	}

	/**
	 * Simple test endpoint to verify service is working
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		logger.info("Service 2 test endpoint called");
		return ResponseEntity.ok("Service 2 is working correctly!");
	}
}
