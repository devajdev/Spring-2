package com.nt.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nt.command.EmployeeCommand;
import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeService;
import com.nt.validator.EmployeeValidtor;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeValidtor validator;
	
	@RequestMapping(value="/insert.htm",method=RequestMethod.GET)
	public  String showForm(@ModelAttribute("empCmd") EmployeeCommand cmd){
		return "employee_insert";
	}
	
	@RequestMapping(value="/insert.htm",method=RequestMethod.POST)
	public String  processForm(Map<String,Object> map,
			                                           @ModelAttribute("empCmd")EmployeeCommand cmd,
			                                            BindingResult errors){
		EmployeeDTO dto=null;
		String result=null;
		if(validator.supports(EmployeeCommand.class)){
			System.out.println("hello1");
			validator.validate(cmd, errors);
			System.out.println(errors.toString()+"hello2");
			if(errors.hasErrors()){
				return "employee_insert";
			}
		}
	
		
		// Convert cmd to DTO
		dto=new EmployeeDTO();
		BeanUtils.copyProperties(cmd,dto);
		
		//use Service
		result=service.register(dto);
		map.put("result",result);
		return "result";
		
	}

}
