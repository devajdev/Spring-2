package com.nt.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages="com.nt.controller")
@Configuration
@EnableWebMvc
public class WebMvcAppConfig {
	
	
	@Bean
	public  SimpleUrlHandlerMapping createSUHMapping(){
		 SimpleUrlHandlerMapping suhm=null;
		 Properties props=null;
		 
		 suhm=new SimpleUrlHandlerMapping();
		 props=new Properties();
		 props.put("/home.htm","pvc");
		 suhm.setMappings(props);
		 suhm.setOrder(Integer.MAX_VALUE-2);
		 return suhm;
	}
	
	
	
	
	@Bean(name="pvc")
	public ParameterizableViewController  createPVController(){
		ParameterizableViewController pvc=null;
		pvc=new ParameterizableViewController();
		pvc.setViewName("welcome");
		return pvc;
		
	}
	
	@Bean
	public ViewResolver createIRVResolver(){
		InternalResourceViewResolver ivr=null;
		ivr=new InternalResourceViewResolver();
		ivr.setPrefix("/WEB-INF/pages/");
		ivr.setSuffix(".jsp");
		return ivr;
		
	}

}
