package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.bo.EmployeeBO;
import com.nt.dao.EmployeeDAO;
import com.nt.dto.EmployeeDTO;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	 private  EmployeeDAO dao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,transactionManager="dsTxMgmr")
	public String register(EmployeeDTO dto) {
		int count=0;
		EmployeeBO bo=null;
		//convert DTO to BO
		bo=new EmployeeBO();
		BeanUtils.copyProperties(dto,bo);
		//use DAO
		count=dao.insert(bo);
	   if(count==0)
		     return "Record not inserted";
	   else 
		   return "Record inserted";
	}//method
}//class
