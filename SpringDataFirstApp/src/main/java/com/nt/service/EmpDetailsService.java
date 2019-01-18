package com.nt.service;

import java.util.List;

import com.nt.dao.EmpDetails;


public interface EmpDetailsService {
	
	public String register(EmpDetails emp);
	public List<EmpDetails> getAllEmpDetails();
	public List<EmpDetails> getEmpsByName(String name);
	public List<EmpDetails> getEmpsByRange(int start,int end);

}
