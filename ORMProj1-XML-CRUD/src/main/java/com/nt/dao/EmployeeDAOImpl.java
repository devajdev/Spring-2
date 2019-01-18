package com.nt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.nt.domain.EmployeeHLO;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static final String HQL_GET_ALL_EMPS="from EmployeeHLO";
	//private static final  String HQL_UPDATE_SAL_BY_RANGE="update EmployeeHLO set salary=salary+(salary*0.1f) where eid>=? and eid<=?";
	private static final  String HQL_UPDATE_SAL_BY_RANGE="update EmployeeHLO set salary=salary+(salary*0.1f) where eid>=:min and eid<=:max";
	private HibernateTemplate ht;
	

	public EmployeeDAOImpl(HibernateTemplate ht) {
		this.ht = ht;
	}


	@Override
	public List<EmployeeHLO> getAllEmployees() {
		List<EmployeeHLO> list=null;
		list=(List<EmployeeHLO>) ht.find(HQL_GET_ALL_EMPS);
		return list;
	}
	
	@Override
	public List<EmployeeHLO> getEmployeesByEIDRange(int start, int end) {
		List<EmployeeHLO> list=null;
		list=(List<EmployeeHLO>) ht.findByNamedQueryAndNamedParam("HQL_GET_EMPS_BY_EID_RANGE",
				                              new String[]{"min","max"},
				                              new Object[]{start,end});
		return list;
	}
	
	@Override
	public int saveEmployee(EmployeeHLO hlo) {
		int idVal=0;
		//ht.setCheckWriteOperations(false);
		idVal=(int) ht.save(hlo);
		
		return idVal;
	}
	
	/*@Override
	public int updateSalary(int start, int end) {
		int count=0;
		count=ht.bulkUpdate(HQL_UPDATE_SAL_BY_RANGE, start,end);
		return count;
	}
	*/
	
	@Override
	public int updateSalary(int start, int end) {
		int cnt=0;
		cnt=ht.execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session ses) throws HibernateException {
				Query query=null;
				int count=0;
				//create Query object
				query=ses.createQuery(HQL_UPDATE_SAL_BY_RANGE);
				//set param values
				query.setInteger("min",start);
				query.setInteger("max",end);
				//execute query
				count=query.executeUpdate();
				return count;
			}
		});
		return cnt;
	}//updateSalary(-,-)
}//class
