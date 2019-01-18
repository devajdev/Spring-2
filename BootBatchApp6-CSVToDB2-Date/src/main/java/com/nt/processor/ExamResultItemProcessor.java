package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;

import com.nt.model.IExamResult;
import com.nt.model.OExamResult;


public class ExamResultItemProcessor implements ItemProcessor<IExamResult, OExamResult>{

	
	public OExamResult process(IExamResult result) throws Exception {
		System.out.println("Processing result :"+result);
		OExamResult  oResult=null;
	
		if(result.getPercentage() < 90){
			return null;
		}
		else{
	     oResult=new OExamResult();
	     oResult.setId(result.getId());
	     oResult.setSem(result.getSem());
	     oResult.setPercentage(result.getPercentage());
	     oResult.setDob(java.sql.Date.valueOf(result.getDob()));
	     return oResult;
		}
    
	}

}
