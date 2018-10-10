package com.miscellaneus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BatchError {

	private String record;
	private LocalDate date;
	private String name;
	private int count;
	private int flag;
	
	
	
	public BatchError() {
		super();
		this.date = LocalDate.now();
		this.name = "Report_" + date.format(DateTimeFormatter.ofPattern("yyyy_LL_dd"));
		this.count = 1;
		this.flag = 0;
		this.record = date.toString() + "|" + this.name + "|" + this.count + "|" + this.flag;
	}


	public static void main(String[] args) {
		BatchError error = new BatchError();
		System.out.println(error.getRecord());
	}


	/**
	 * @return the record
	 */
	public String getRecord() {
		return record;
	}

}
