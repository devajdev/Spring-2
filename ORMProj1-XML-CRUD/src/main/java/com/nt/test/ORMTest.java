package com.nt.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeService;

public class ORMTest {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		EmployeeService service=null;
		List<EmployeeDTO> listDTO=null;
		EmployeeDTO dto1=null;
		//create IOC container
		ctx=new ClassPathXmlApplicationContext("com/nt/cfgs/applicationContext.xml");
		//get Bean
		service=ctx.getBean("empService",EmployeeService.class);
		try{
		//invoke methods
		listDTO=service.fetchAllEmployees();
		listDTO.forEach(dto->{
			System.out.println(dto.getEid()+" "+dto.getFirstName()+" "+dto.getLastName()+" "+dto.getSalary());
		});
		
		System.out.println("-------------------------------------");
		listDTO=service.fetchAllEmployeesByRange(100, 200);
		listDTO.forEach(dto->{
			System.out.println(dto.getEid()+" "+dto.getFirstName()+" "+dto.getLastName()+" "+dto.getSalary());
		});
		System.out.println(".......................................");
		
		/*dto1=new EmployeeDTO();
		dto1.setFirstName("ramesh");
		dto1.setLastName("rao");
		dto1.setSalary(9000);
		
		System.out.println(service.registerEmployee(dto1));
		*/
		System.out.println("----------------------------------");
		System.out.println(service.hikeEmpsSalaryByRange(100, 200));
		}//try
		catch(DataAccessException dae){
			System.out.println("Internal problem-->"+dae.getMessage());
		}
		
		//close container
		((AbstractApplicationContext) ctx).close();
		

	}

}
