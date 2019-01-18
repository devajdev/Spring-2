package com.nt.service;

import java.util.List;

import com.nt.dto.EmployeeDTO;

public interface EmployeeService {
	public List<EmployeeDTO> fetchAllEmployees(); 
	public List<EmployeeDTO> fetchAllEmployeesByRange(int start,int end);
	public String registerEmployee(EmployeeDTO dto);
	public String hikeEmpsSalaryByRange(int start,int end);
}
