package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class EmployeeRecordApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecordApp1Application.class, args);
	}

}
