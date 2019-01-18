package com.nt.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.bo.EmployeeBO;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private static final String  GET_ALL_EMPLOYESS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP";
	@Autowired
	private  JdbcTemplate jt;

	@Override
	public List<EmployeeBO> getAllEmployees() {
		System.out.println(jt.getDataSource().getClass());
		List<EmployeeBO> listBO=null;
		listBO=jt.query(GET_ALL_EMPLOYESS,
				                       rs->{
				                    	   List<EmployeeBO> listBO1=null;
				                    	   listBO1=new ArrayList();
				                    	   while(rs.next()){
				                    		   EmployeeBO bo=null;
				                    		   bo=new EmployeeBO();
				                    		   bo.setEno(rs.getInt(1));
				                    		   bo.setEname(rs.getString(2));
				                    		   bo.setJob(rs.getString(3));
				                    		   bo.setSalary(rs.getInt(4));
				                    		   bo.setDeptNo(rs.getInt(5));
				                    		   listBO1.add(bo);
				                    	   }
				                    	   return   listBO1;
				                       });
		return listBO;
	}//method

}//class
