package com.nt.test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.nt.config.RootAppConfig;
import com.nt.config.WebMvcAppConfig;


public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebMvcAppConfig.class,BootMvcSecurityApp2Application.class);
	}

}
