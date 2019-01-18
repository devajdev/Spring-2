package com.nt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.nt.config.RootAppConfig;

@SpringBootApplication
@Import(value=RootAppConfig.class)
public class BootMvcSecurityApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(BootMvcSecurityApp2Application.class, args);
	}
}
