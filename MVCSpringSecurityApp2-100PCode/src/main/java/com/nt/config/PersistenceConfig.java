package com.nt.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class PersistenceConfig {
	
	
	@Bean(name="jofb")
	public JndiObjectFactoryBean createJofb(){
		JndiObjectFactoryBean jofb=null;
		jofb=new JndiObjectFactoryBean();
		jofb.setJndiName("java:/comp/env/DsJndi");
		return jofb;
	}
	
	@Bean(name="jofbDs")
	@Primary
	public DataSource createDataSource(){
		return (DataSource)createJofb().getObject();
		
	}

}
