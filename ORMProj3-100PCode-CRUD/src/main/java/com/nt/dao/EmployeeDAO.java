package com.nt.dao;

import java.util.List;

import com.nt.domain.EmployeeHLO;

public interface EmployeeDAO {
	public List<EmployeeHLO>  getAllEmployees();
	public List<EmployeeHLO> getEmployeesByEIDRange(int start,int end);
	public int saveEmployee(EmployeeHLO hlo);
	public int updateSalary(int start,int end);

}
