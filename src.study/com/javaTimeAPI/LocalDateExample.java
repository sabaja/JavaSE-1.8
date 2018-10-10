package com.javaTimeAPI;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

public class LocalDateExample {

	public static void main(String[] args) {
		
		//Current Date
		LocalDate today = LocalDate.now();
		System.out.println("Today date: " + today);

		
		//Creating LocalDate by providing input arguments
		LocalDate date1 = LocalDate.of(1976, 9, 17);
		LocalDate date2 = LocalDate.of(2008, 3, 19);
		System.out.println("My birthday: " + date1 + "\nmy son's birthday " + date2);
		
		
		//Current date in "Samoa", you can get it from ZoneId javadoc
		LocalDate usSamoaDate = LocalDate.now(ZoneId.of("US/Samoa"));
		System.out.println("Samoa Date: " + usSamoaDate);
		
		
		//Getting date from the base date i.e 01/01/1970
		LocalDate dateFromBase = LocalDate.ofEpochDay(365);
		System.out.println("365th day from base date: " + dateFromBase);
		
		
		LocalDate dayOfYear = LocalDate.ofYearDay(2013, 7);
		System.out.println("7th of 2013: " + dayOfYear);
		
		
		//Parsed date with an ISO
		LocalDate parsedDate = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_DATE);
		System.out.println("Parsed basic Date: " + parsedDate);
		
	
	}

}
