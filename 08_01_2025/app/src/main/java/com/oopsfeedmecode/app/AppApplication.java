package com.oopsfeedmecode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
	private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);
	public static void main(String[] args) {
		logger.atDebug().setMessage("Starting app").log();
		SpringApplication.run(AppApplication.class, args);
		logger.atDebug().setMessage("Started app").log();
	}
}