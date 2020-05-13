package com.sample.recaptcha.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample.recaptcha")
public class GoogleRecaptchaApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoogleRecaptchaApplication.class, args);
	}
}
