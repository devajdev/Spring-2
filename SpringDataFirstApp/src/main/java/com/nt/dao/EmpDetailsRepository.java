package com.nt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface EmpDetailsRepository extends CrudRepository<EmpDetails, Long> {

	public List<EmpDetails> findByEname(String ename);
	
	@Query(value="from EmpDetails where eno>=:start and eno<=:end")
	public List<EmpDetails> findBetweenByEno(@Param(value = "start") int start,@Param(value="end")int end);
}
