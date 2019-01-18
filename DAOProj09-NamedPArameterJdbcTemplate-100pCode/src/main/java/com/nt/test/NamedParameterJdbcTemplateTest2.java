package com.nt.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.nt.config.AppConfig;
import com.nt.dto.DepartmentDTO;
import com.nt.service.DepartmentService;


public class NamedParameterJdbcTemplateTest2 {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		DepartmentService  service=null;
		List<DepartmentDTO> listDTO=null;
		DepartmentDTO dto1=null;
		//create IOC container
		ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		//get Bean
		service=ctx.getBean("deptService",DepartmentService.class);
		//invoke method
		listDTO=service.searchDeptsByDeptNos(1, 40);
		listDTO.forEach(dto->{
			System.out.println(dto.getDeptNo()+"  "+dto.getDeptName()+" "+dto.getLocation());
		});
		System.out.println("Dept Count:::"+service.findDeptsCountByLocations("DALLAS","NEW YORK"));
		//pepare DTO
		dto1=new DepartmentDTO();
		dto1.setDeptNo(13); dto1.setDeptName("CA"); dto1.setLocation("HYD");
		System.out.println(service.addDepartment(dto1));
		
		//close IOC container
		((AbstractApplicationContext) ctx).close();
	}//main
}//class
