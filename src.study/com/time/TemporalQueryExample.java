package com.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

public class TemporalQueryExample implements TemporalQuery<DayOfWeek> {

	public static void main(String[] args) {
		
		//TemporalQueries ti permette di capire che tipo di data è quella passata
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = LocalDateTime.now();
		TemporalQuery<?> queries = TemporalQueries.precision();
		System.out.println("Date: " + date.query(queries) + "\nTime: " + dateTime.query(queries));
		
		//Per capire che giorno della settimana è oggi
		
		System.out.println("Day of the week: " + new TemporalQueryExample().queryFrom(date));
	}

	//Per scoprire che giorno è della settimana con query
	@Override
	public DayOfWeek queryFrom(TemporalAccessor temporal) {
		LocalDate date = LocalDate.from(temporal);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek;
	}

}
