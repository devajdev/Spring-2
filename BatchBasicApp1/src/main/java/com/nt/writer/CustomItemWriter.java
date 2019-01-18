package com.nt.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> booksListWithAuthors) throws Exception {
	  System.out.println("CustomWriter:writer(-)");
		System.out.println(booksListWithAuthors);
	}

}
