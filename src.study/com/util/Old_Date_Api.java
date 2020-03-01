package com.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;



public class Old_Date_Api {

	public static void main(String[] args){ 
		printTodayDate();
		printTodayDateLocale();
		printHourOftheDay();
		
	}
	
	public static void printHourOftheDay(){
		Calendar cal = Calendar.getInstance();//istanza locale default
		cal.set(Calendar.HOUR_OF_DAY, getTimeZoneHourOfTheDay());//chiamata privata
		Formatter f = new Formatter(System.out);		
		f.format("%tH", cal.getTime());
	}

	private static int getTimeZoneHourOfTheDay(){
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("America/New York"));
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	
	public static int getNumberOfWeek(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int numSet = cal.get(Calendar.DAY_OF_WEEK);
		return numSet;
	}

	public static void printFileIdTimeZone(){
		//nel file IdTimeZone scrivo  gli zoneID
		try {
			FileOutputStream fos = new FileOutputStream(new File("IDtimeZone.txt"));
			PrintStream ps = new PrintStream(fos);
			String[] zoneId = TimeZone.getAvailableIDs(); 
			for(String i : zoneId){
				ps.println(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return; 
	}
	

	
	public static void printTodayDate(){
			DateFormat f = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss", Locale.getDefault());
			String s1 = f.format(new Date());
			String s = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.ITALY).format(new Date());
			System.out.printf("Italy: %s\n%s", s1, s);
	}
	
	public static void printTodayDateLocale(){
		Locale locale = Locale.UK;
		DateFormat date = DateFormat.getDateInstance(DateFormat.ERA_FIELD, locale);
		String s = date.format(new Date());
		System.out.println("UK: " + s);
		
		Locale l = Locale.JAPAN;
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, l);
		String da = df.format(new Date());
		System.out.println("Japan: " + da);
	}
}
