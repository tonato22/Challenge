package com.example.Ch3.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Ch3")
public class Ch3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch3Application.class, args);
	}

}
