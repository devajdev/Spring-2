package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;

import com.nt.model.ExamResult;


public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult>{

	
	public ExamResult process(ExamResult result) throws Exception {
		System.out.println("Processing result :"+result);
	
		if(result.getPercentage() < 90){
			return null;
		}
		
		return result;
	}

}
