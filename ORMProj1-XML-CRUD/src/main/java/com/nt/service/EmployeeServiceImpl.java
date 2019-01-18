package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nt.dao.EmployeeDAO;
import com.nt.domain.EmployeeHLO;
import com.nt.dto.EmployeeDTO;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO dao;
	

	public EmployeeServiceImpl(EmployeeDAO dao) {
		this.dao = dao;
	}


	@Override
	public List<EmployeeDTO> fetchAllEmployees() {
		List<EmployeeHLO> listHLO=null;
		List<EmployeeDTO> listDTO=new ArrayList();
		//use DAO
		listHLO=dao.getAllEmployees();
		//convert listHLO to listDTO
		listHLO.forEach(hlo->{
              EmployeeDTO dto=new EmployeeDTO();
              BeanUtils.copyProperties(hlo,dto);
              listDTO.add(dto);
		});
		return listDTO;
	}
	
	@Override
	public List<EmployeeDTO> fetchAllEmployeesByRange(int start, int end) {
		List<EmployeeHLO> listHLO=null;
		List<EmployeeDTO> listDTO=new ArrayList();
		//use dAO
		listHLO=dao.getEmployeesByEIDRange(start, end);
		//convert listHLO to listDTO
				listHLO.forEach(hlo->{
		              EmployeeDTO dto=new EmployeeDTO();
		              BeanUtils.copyProperties(hlo,dto);
		              listDTO.add(dto);
				});
				return listDTO;
	}
	
	@Override
	public String registerEmployee(EmployeeDTO dto) {
		EmployeeHLO hlo=null;
		int idVal=0;
		//Convert DTO to HLO
		hlo=new EmployeeHLO();
		BeanUtils.copyProperties(dto,hlo);
		//use DAO
		idVal=dao.saveEmployee(hlo);
		return "Employee saved with idVal::"+idVal;
	}
	
	@Override
	public String hikeEmpsSalaryByRange(int start, int end) {
		int count=0;
		//use DAO
		count=dao.updateSalary(start, end);
		return count+" number of employee salaries are hiked";
	}

}
