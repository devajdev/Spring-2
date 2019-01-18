package com.nt.model;

import java.sql.Date;

public class ExamResult {
	private int id;
	private int sem;
	private double percentage;
	


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ExamResult [id=" + id + ", sem=" + sem +", percentage=" + percentage + "]";
	}

	
	
	
}
