package com.javaTimeAPI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExample {

	public static void main(String[] args) {

		// Current Date
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("Date and time " + dateTime);
		System.out.println("DateTime in los Angeles " + dateTime.atZone(ZoneId.of("America/Los_Angeles")) 
		+ " and offset " + (dateTime.atZone(ZoneId.of("America/Los_Angeles"))).getOffset() + " Hours" );

		// current date and time
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println("Here Datetime: " + ldt + "\nDateTime of Bangkok "
				+ LocalDateTime.of(LocalDate.now(ZoneId.of("Asia/Bangkok")), LocalTime.now(ZoneId.of("Asia/Bangkok"))));

		// specific parse
		LocalDateTime specific = LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_DATE_TIME);
		System.out.println("Now parsed DateTim " + specific);

		// Getting date from the base date i.e 01/01/1970
		LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(1460030000, 555666999, ZoneOffset.UTC);
		System.out.println("10000th second time from 01/01/1970= " + dateFromBase);
	}

}
