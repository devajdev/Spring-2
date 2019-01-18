package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.dao.EmpSearchDAO;

public class EmpSearchServiceImpl implements EmpSearchService {
	private EmpSearchDAO dao;

	public EmpSearchServiceImpl(EmpSearchDAO dao) {

		this.dao = dao;
	}

	@Override
	public List<Map<String, Object>> searchEmpsByDesgs(String[] desg) {
		StringBuffer cond=new StringBuffer("(");
		List<Map<String,Object>>  list=null;
		//convert desg[] to SQL condition String ('CLERK','MANAGER','ANALYST')
		for(int i=0;i<desg.length;++i){
			if(i==desg.length-1)
				cond.append("'"+desg[i]+"')");
			else
				cond.append("'"+desg[i]+"',");
		}
		//use DAO
		list=dao.findEmpsByDesgs(cond.toString());
		return list;
	}//method
}//class
