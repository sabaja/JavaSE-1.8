package com.javaTimeAPI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalTimeExample {

	public static void main(String[] args) {
		
		//Current Time
		LocalTime nowTime = LocalTime.now();
		System.out.println("Now: " + nowTime);

		
		//Creating LocalTime by providing input arguments
		LocalTime specificTime = LocalTime.of(12, 5, 6, 999_999_999);
		System.out.println("Specific time of day: " + specificTime + " " + specificTime.minusHours(4) + " " + specificTime.minusNanos(111)
							+ " " + specificTime.plus(23, ChronoUnit.MINUTES));
		
		
		//Current Time in "Samoa", you can get it from ZoneId javadoc
		LocalTime usSamoatime = LocalTime.now(ZoneId.of("US/Samoa"));
		System.out.println("Samoa time: " + usSamoatime);
		
		
		//Getting date from the base date i.e 01/01/1970
		LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
		System.out.println("10000th second time "+specificSecondTime); 
		
		//Parsing specific time
		System.out.println("Specific System time " + LocalTime.parse(LocalTime.now(ZoneId.of("SystemV/MST7")).plusNanos(666_666_666).toString(), DateTimeFormatter.ISO_TIME)); 
		
	}

}
