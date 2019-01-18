package com.nt.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.nt.domain.EmployeeHLO;

@Configuration
@ComponentScan(basePackages="com.nt.dao")
@PropertySource(value="classpath:com/nt/commons/jdbc.properties")
public class PersistenceConfig {
	@Autowired
	private Environment env;
	
	@Bean(name="dbcpDs")
	public  DataSource createBasicDataSource(){
		BasicDataSource bds=null;
		bds=new BasicDataSource();
		bds.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
		bds.setUrl(env.getRequiredProperty("jdbc.url"));
		bds.setUsername(env.getRequiredProperty("jdbc.user"));
		bds.setPassword(env.getRequiredProperty("jdbc.pwd"));
		return bds;
	}
	
	@Bean(name="localSesFact")
	public  LocalSessionFactoryBean createLocalSesFact(){
		LocalSessionFactoryBean sesfact=null;
		Properties props=null;
		sesfact=new LocalSessionFactoryBean();
		sesfact.setDataSource(createBasicDataSource());
		sesfact.setAnnotatedClasses(EmployeeHLO.class);
		sesfact.setAnnotatedPackages("com.nt.domain");
		
		props=new Properties();
		props.put("hibernate.show_sql",true);
		props.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		sesfact.setHibernateProperties(props);
		
		return sesfact;
		
	}
	
	@Bean(name="sesfact")
	public SessionFactory createSessionFactory(){
		return  createLocalSesFact().getObject();
	}
	
	@Bean(name="ht")
	public  HibernateTemplate createHibernateTemplate(){
		return new HibernateTemplate(createSessionFactory());
	}

}
