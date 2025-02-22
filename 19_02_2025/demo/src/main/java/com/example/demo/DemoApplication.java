package com.example.demo;

import com.example.demo.package1.Nice;
import com.example.demo.package2.NotSoNice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


		Nice.methodWithInfoLogs();

		Nice.methodWithDebugLogs();

		NotSoNice.methodWithInfoLogs();

		NotSoNice.methodWithDebugLogs();
	}
}
