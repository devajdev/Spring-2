package com.nt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.command.EmployeeCommand;

@Component
public class EmployeeValidtor implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		  return clazz.isAssignableFrom(EmployeeCommand.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		EmployeeCommand cmd=null;
		//type casting
		cmd=(EmployeeCommand)command;
		if(cmd.getEno()<=0 || cmd.getEno()>200000 )
			errors.rejectValue("eno","Range.empCmd.eno");
		if(cmd.getEname().equals("") || cmd.getEname().length()==0)
			errors.rejectValue("ename","NotEmpty.empCmd.ename");
		if(cmd.getJob().equals("") || cmd.getJob().length()==0)
			errors.rejectValue("job","NotEmpty.empCmd.job");
		
		if(cmd.getSalary()<=5000 || cmd.getSalary()>300000 )
			errors.rejectValue("salary","Range.empCmd.salary");
		

	}

}
