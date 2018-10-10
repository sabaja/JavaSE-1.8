package com.javaTimeAPI;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.dateApiRegexUtilExercises.Dictionary;
import com.dateApiRegexUtilExercises.Languages;
import com.dateApiRegexUtilExercises.StringUtils;

public class DateUtilsTest {

	public static void main(String[] args) {

	System.out.println(StringUtils.translate(Languages.Italian, Dictionary.Table));
	testFindRegex();
	testDate();
	

	}

	public static void testFindRegex(){
		String str = "The more I give to thee,"
				+ "the more I have, "
				+ "for both are infinite.";
		String regex = "t[a-zA-Z]";
		List<String> result = StringUtils.findByRegex(regex, str);
		for(String s : result){
			if(s != null)
				System.out.println(s);
		}
			
	}
	
	public static void testDate(){
		System.out.println(DateUtils.getDurationByType(LocalDate.parse("2013-01-07"), LocalDate.now(), ChronoUnit.YEARS));
		System.out.println(DateUtils.getDurationByType(Instant.parse("2013-01-07T03:45:00.00Z"), Instant.now(), ChronoUnit.MINUTES));
		System.out.println(DateUtils.getDurationByType(LocalDate.ofYearDay(2001, LocalDate.of(2001, Month.SEPTEMBER, 11).getDayOfYear()), LocalDate.now(), ChronoUnit.DAYS));
		System.out.println(DateUtils.getTime());
		System.out.println(DateUtils.getFormatDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Minsk")), "yyyy-mm-dd mm:hh:ss"));
		System.out.println(DateUtils.getFormatDate(LocalDateTime.of(LocalDate.now(), LocalTime.NOON),DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(DateUtils.queryDate("23-11-2014", "dd-MM-yyyy"));
	}
}
