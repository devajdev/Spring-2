package com.nt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.bo.EmployeeBO;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private static final String  EMPLOYEE_INSERT_QUERY="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(?,?,?,?)";
	@Autowired
	private JdbcTemplate jt;

	@Override
	public int insert(EmployeeBO bo) {
		int count=0;
		count=jt.update(EMPLOYEE_INSERT_QUERY,bo.getEno(),bo.getEname(),bo.getJob(),bo.getSalary());
		return count;
	}

}
