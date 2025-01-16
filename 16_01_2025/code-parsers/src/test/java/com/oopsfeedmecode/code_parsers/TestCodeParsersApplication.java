package com.oopsfeedmecode.code_parsers;

import org.springframework.boot.SpringApplication;

public class TestCodeParsersApplication {

	public static void main(String[] args) {
		SpringApplication.from(CodeParsersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
