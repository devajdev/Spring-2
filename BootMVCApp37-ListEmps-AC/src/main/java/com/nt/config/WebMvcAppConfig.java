package com.nt.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

@Configuration
@ComponentScan(basePackages="com.nt.controller")
public class WebMvcAppConfig {
	
	@Bean
	public SimpleUrlHandlerMapping createSHMapping(){
		 SimpleUrlHandlerMapping mapping=null;
		 Properties props=null;
		 mapping=new SimpleUrlHandlerMapping();
		 props=new Properties();
		 props.put("/home.htm", "pvc");
		 mapping.setMappings(props);
		 mapping.setOrder(Integer.MAX_VALUE-2);
		 return mapping;
	}
	
	@Bean(name="pvc")
	public  ParameterizableViewController  createPVController(){
		ParameterizableViewController pvc=null;
		pvc=new ParameterizableViewController();
		pvc.setViewName("welcome");
		return pvc;
	}

}
