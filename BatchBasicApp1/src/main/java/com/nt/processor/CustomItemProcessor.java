package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor  implements ItemProcessor<String,String> {

	@Override
	public String process(String bookWithOutAuthor) throws Exception {
		System.out.println("CustomItemProcessor:proces(-)");
		if(bookWithOutAuthor.equalsIgnoreCase("CRJ"))
		   return bookWithOutAuthor+"--->HS,PN";
		else if(bookWithOutAuthor.equalsIgnoreCase("TIJ"))
		   return bookWithOutAuthor+"---->BE";
		else if(bookWithOutAuthor.equalsIgnoreCase("SIJ"))
			return bookWithOutAuthor+"---> Ameerpet Student";
		else if(bookWithOutAuthor.equalsIgnoreCase("LIJ"))
			return bookWithOutAuthor+"----> NitStudents";
		else if(bookWithOutAuthor.equalsIgnoreCase("SL"))
			return bookWithOutAuthor+"-----> Rod Jhoson";
		else
			return  bookWithOutAuthor;
		
	}

}
