package com.nt.config;

import java.util.Properties;

import javax.sql.DataSource;

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
public class PersistenceConfig {
	@Autowired
	private DataSource ds;
	
	
	@Bean(name="localSesFact")
	public  LocalSessionFactoryBean createLocalSesFact(){
		System.out.println("ds::::"+ds.getClass());
		LocalSessionFactoryBean sesfact=null;
		Properties props=null;
		sesfact=new LocalSessionFactoryBean();
		sesfact.setDataSource(ds);
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
