package com.nt.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemReader<String> {
	private List<String> booksList;
	private int bookCount;

	public void setBooksList(List<String> booksList) {
		this.booksList = booksList;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("ItemReader::read()");
		if(bookCount < booksList.size()){
			return booksList.get(bookCount++);
		}
		else{
			return null;
		}

	}

}
