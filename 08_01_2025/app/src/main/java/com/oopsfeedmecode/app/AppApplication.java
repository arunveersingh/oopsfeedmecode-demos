package com.oopsfeedmecode.app;


import com.oopsfeedmecode.lib_using_slf4j_api.utils.EncryptionDecryptionUtil;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import com.oopsfeedmecode.lib_using_slf4j_api.utils.NewEncryptionDecryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;

@SpringBootApplication
public class AppApplication {
	//private static final Logger logger = LogManager.getLogger(AppApplication.class.getName());
	// slf4j logger
	private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		logger.debug("Starting app");
		SpringApplication.run(AppApplication.class, args);
		logger.debug("Started app");


		/**
		 * Demonstrate below code with EncryptionDecryptionUtil. It will break
		 * without log4j-to-slf4j bridge on path
		 *
		 * Demonstrate NewEncryptionDecryptionUtil with and without log4j-to-slf4j.
		 * Code will not break. Logger format will vary but it will break.
		 */
		SecretKey key = NewEncryptionDecryptionUtil.generateKey();
		String plaintext = "Hello from the JUnit test!";
		// Encrypt
		String encrypted = NewEncryptionDecryptionUtil.encrypt(plaintext, key);
		logger.debug("encrypted" + encrypted);
	}
}