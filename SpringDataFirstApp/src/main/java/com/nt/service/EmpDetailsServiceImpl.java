package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.EmpDetailsRepository;
import com.nt.dao.EmpDetails;

@Service("empService")
public class EmpDetailsServiceImpl implements EmpDetailsService {
	@Autowired
	private EmpDetailsRepository empDetailsRepository;

	@Override
	public String register(EmpDetails emp) {
		EmpDetails resultEmp;
		//prepare Domain obj
		resultEmp=empDetailsRepository.save(emp);
		if(resultEmp!=null)
		   return "Registration successuful with id"+resultEmp.getEno();
		else
			return "Registration failed";
	}
	
	@Override
	public List<EmpDetails> getAllEmpDetails() {
		List<EmpDetails> listEmps;
		listEmps=(List<EmpDetails>) empDetailsRepository.findAll();
		return listEmps;
	}
	
	@Override
	public List<EmpDetails> getEmpsByName(String name) {
		
		return empDetailsRepository.findByEname(name);
   }
	
	@Override
	public List<EmpDetails> getEmpsByRange(int start, int end) {
		
		return empDetailsRepository.findBetweenByEno(start, end);
	}
	

}
