package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(value="/listEmps.htm")
	public String  process(Map<String,Object> map){
		List<EmployeeDTO> listDTO=null;
		//use Service
		listDTO=service.fetchAllEmployees();
		//add results to Model Attribute
		map.put("empList", listDTO);
		return  "list_emps";
	}

}
