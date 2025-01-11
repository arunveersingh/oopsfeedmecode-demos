package com.oopsfeedmecode.app;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
	private static final Logger logger = LogManager.getLogger(AppApplication.class.getName());

	public static void main(String[] args) {
		logger.debug("Starting app");
		SpringApplication.run(AppApplication.class, args);
		logger.debug("Started app");
	}
}