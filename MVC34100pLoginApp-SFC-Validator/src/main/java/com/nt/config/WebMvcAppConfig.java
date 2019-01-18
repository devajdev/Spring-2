package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nt.interceptor.DoublePostingPreventingInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.nt.controller"})
public class WebMvcAppConfig  implements WebMvcConfigurer {
	
/*	@Bean
	public HandlerMapping createRequestMappingHandlerMapping(){
		RequestMappingHandlerMapping mapping=null;
		mapping=new RequestMappingHandlerMapping();
		mapping.setInterceptors(new DoublePostingPreventingInterceptor());
		return mapping;
		
	}*/

	@Bean
	public ViewResolver createIRVResolver(){
		InternalResourceViewResolver irvr=null;
		irvr=new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/pages/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
	@Bean(name="messageSource")
	public  ResourceBundleMessageSource  createRBMS(){
		ResourceBundleMessageSource rbms=null;
		rbms=new ResourceBundleMessageSource();
		rbms.setBasename("com/nt/commons/validator");
       return rbms;		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DoublePostingPreventingInterceptor());
	
	}
}
