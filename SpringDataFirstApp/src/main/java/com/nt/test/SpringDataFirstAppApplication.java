package com.nt.test;

import java.util.List;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.nt.config.AppConfig;
import com.nt.dao.EmpDetails;
import com.nt.service.EmpDetailsService;

@SpringBootApplication
@Import(value=AppConfig.class)
public class SpringDataFirstAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		EmpDetailsService  service=null;
		com.nt.dao.EmpDetails details=null;
		List<EmpDetails> list=null;
		ctx=SpringApplication.run(SpringDataFirstAppApplication.class, args);
		//get Bean
		service=ctx.getBean("empService",EmpDetailsService.class);
		//prpare obj
		details=new EmpDetails();
		//details.setEno(2);
		details.setEname("ramesh"); details.setDesg("CLERK");
		details.setSalary(9000);
		//invoke method
		//System.out.println(service.register(details));
		list=service.getAllEmpDetails();
		list.forEach(emp->{
			System.out.println(emp.getEno()+" "+emp.getEname()+" "+emp.getSalary()+" "+emp.getDesg());
		});
		System.out.println("========================");
		
		list=service.getEmpsByName("raja");
		list.forEach(emp->{
			System.out.println(emp.getEno()+" "+emp.getEname()+" "+emp.getSalary()+" "+emp.getDesg());
		});
		System.out.println("========================");
		list=service.getEmpsByRange(1, 2);
		list.forEach(emp->{
			System.out.println(emp.getEno()+" "+emp.getEname()+" "+emp.getSalary()+" "+emp.getDesg());
		});
		
		
		//close container
		((ConfigurableApplicationContext) ctx).close();
		
		
	}
}
